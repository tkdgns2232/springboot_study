package com.korit.springboot_study.controller.advice;

import com.korit.springboot_study.dto.response.common.BadRequestResponseDto;
import com.korit.springboot_study.dto.response.common.NotFoundResponseDto;
import com.korit.springboot_study.exception.CustomDuplicateKeyException;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // controller에서 나오는 오류들을 여기로 몰아서 처리하게하는 코드
public class GlobalRestControllerAdvice {

    @ExceptionHandler(value = NotFoundException.class )
    public ResponseEntity<NotFoundResponseDto<?>> notFound(NotFoundException e) {
        return ResponseEntity.status(404).body(new NotFoundResponseDto<>(e.getMessage()));
    }
    // 응답을 성공과 실패로 나눔
    @ExceptionHandler(value = CustomDuplicateKeyException.class ) // CustomDuplicateKeyException을 사용하면 sql오류를 여기서 변경할 수 있다.
    public ResponseEntity<BadRequestResponseDto<?>> duplicatekey(CustomDuplicateKeyException e) {
        return ResponseEntity.status(400).body(new BadRequestResponseDto<>(e.getErrors()));
    }
}
