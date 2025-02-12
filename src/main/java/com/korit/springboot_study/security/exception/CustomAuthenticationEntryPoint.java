package com.korit.springboot_study.security.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper objectMapper;

    @Override // 인증 예외가 발생했을 때 호출되는 메서드
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        System.out.println("인증 예외 발생");
        authException.printStackTrace(); // 예외 상세 출력
        response.setStatus(403); // 403 Forbidden 상태 코드 설정
        response.setContentType("application/json"); // 응답 타입을 JSON으로 설정
        response.setCharacterEncoding("UTF-8"); // 문자 인코딩을 UTF-8로 설정
        // 오류 메시지를 JSON 형태로 응답
        response.getWriter().println(objectMapper.writeValueAsString(Map.of("error", "인증필요합니다!!!")));
    }
}
