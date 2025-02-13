package com.korit.springboot_study.controller;

import com.korit.springboot_study.dto.request.ReqSigninDto;
import com.korit.springboot_study.dto.request.ReqSignupDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.User;
import com.korit.springboot_study.service.AuthenticationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.lang.reflect.MalformedParameterizedTypeException;

@RestController
@Api(tags = "계정 API")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/api/auth/signup")
    @ApiOperation(value = "회원가입")
    public ResponseEntity<SuccessResponseDto<User>> singup(@Valid @RequestBody ReqSignupDto reqSignupDto) throws MalformedParameterizedTypeException {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(authenticationService.signup(reqSignupDto)));
    }

    @PostMapping("/api/auth/signin")
    @ApiOperation(value = "로그인")
    public ResponseEntity<SuccessResponseDto<String>> signin(@Valid @RequestBody ReqSigninDto reqSigninDto) throws MalformedParameterizedTypeException {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(authenticationService.signin(reqSigninDto)));
    }

}
