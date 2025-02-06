package com.korit.springboot_study.controller;

import com.korit.springboot_study.dto.request.ReqAddBookDto;
import com.korit.springboot_study.dto.request.study.ReqAddMajorDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.Book;
import com.korit.springboot_study.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/api/study/book")
    public ResponseEntity<SuccessResponseDto<Book>> addBook(@Valid @RequestBody ReqAddBookDto reqAddBookDto) {
        System.out.println(reqAddBookDto);

        return ResponseEntity.ok().body(bookService.addBook(reqAddBookDto));
    }
}
