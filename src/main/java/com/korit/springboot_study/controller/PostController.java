package com.korit.springboot_study.controller;

import com.korit.springboot_study.dto.request.ReqCreatePostDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.Post;
import com.korit.springboot_study.service.PostService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@Api(tags="게시글 API")
public class PostController {

    @Autowired
    private PostService postService;

    // Create
    @PostMapping("/api/post")
    public ResponseEntity<SuccessResponseDto<Post>> createPost(@RequestBody ReqCreatePostDto reqCreatePostDto) {
        return ResponseEntity
                .created(URI.create(""))
                .body(new SuccessResponseDto<>(postService.createPost(reqCreatePostDto)));
        //.created()은 201번 url를 매개변수로 전달 ex).created(URI.create("http://~~~~~~")
    }

    //Read(단건)
    @GetMapping("/api/post{postId}")
    public ResponseEntity<?> getPost(@PathVariable int postId) throws Exception {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(postService.getPostById(postId)));
    }

    //Read(다건)
    @GetMapping("/api/posts")
    public ResponseEntity<SuccessResponseDto<List<Post>>> getPosts(
            @RequestParam(required = true) int page,
            @RequestParam(required = true) int size,
            @RequestParam(required = false, defaultValue = "") String keyword) throws Exception {
        return ResponseEntity
                .ok()
                .body(new SuccessResponseDto<>(postService.getAllPostsByKeywordContaining(page,size,keyword)));
    }
    // create_at 은 문자열로 저장하는게 편하다

    @PostMapping("/api/post/{postId}/like")
    public ResponseEntity<SuccessResponseDto<Boolean>> likePost(@PathVariable int postId) throws Exception {
        int userId = 2;
        return ResponseEntity.ok().body(new SuccessResponseDto<>(postService.likePost(postId,userId)));
    }

    @PostMapping("/api/post/{postId}/dislike")
    public ResponseEntity<SuccessResponseDto<Post>> dislikePost(@PathVariable int postId) throws Exception {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(null));
    }

}
