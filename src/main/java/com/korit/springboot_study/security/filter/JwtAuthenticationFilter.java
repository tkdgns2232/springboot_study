package com.korit.springboot_study.security.filter;

import com.korit.springboot_study.repository.UserRepository;
import com.korit.springboot_study.security.jwt.JwtProvider;
import com.korit.springboot_study.security.principal.PrincipalUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter implements Filter {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest; // HttpServletRequest 다운 캐스팅

        // Bearer Token(JWT)
        String authorization = request.getHeader("Authorization"); // 다운캐스팅하면 getHeader 사용가능

        if (jwtProvider.validateToken(authorization)) {
            setJwtAuthentication(authorization);
        }

        filterChain.doFilter(servletRequest, servletResponse); // 다음 filter 호출

    }

    private void setJwtAuthentication(String bearerToken){
        Claims claims = jwtProvider.parseToken(bearerToken);
        if(claims == null){
            throw new JwtException("Invalid JWT token");
        }
        int userId = Integer.parseInt(claims.get("userId").toString());
        userRepository.findById(userId).ifPresent(user -> {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            PrincipalUser principalUser = new PrincipalUser(user);
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalUser,principalUser.getPassword(),principalUser.getAuthorities());
            securityContext.setAuthentication(authentication);
        });
    }
}
