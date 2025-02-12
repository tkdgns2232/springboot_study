package com.korit.springboot_study.security.filter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Component
public class CustomAuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 익명 사용자 객체 생성
        UserDetails principalUser = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of(); // 권한 설정 (이 예시에서는 권한 없음)
            }

            @Override
            public String getPassword() {
                return "aaaa"; // 임의의 비밀번호
            }

            @Override
            public String getUsername() {
                return "aaaa"; // 임의의 사용자명
            }

            @Override
            public boolean isAccountNonExpired() {
                return true; // 계정 만료 여부
            }

            @Override
            public boolean isAccountNonLocked() {
                return true; // 계정 잠금 여부
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true; // 자격 증명 만료 여부
            }

            @Override
            public boolean isEnabled() {
                return true; // 계정 활성화 여부
            }
        };

        // 사용자 인증 토큰 생성
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principalUser, "", principalUser.getAuthorities());
        // 인증 정보를 SecurityContext 에 설정
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 필터 체인을 계속 진행
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
