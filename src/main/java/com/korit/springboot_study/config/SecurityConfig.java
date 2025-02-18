package com.korit.springboot_study.config;

import com.korit.springboot_study.security.exception.CustomAuthenticationEntryPoint;
import com.korit.springboot_study.security.filter.CustomAuthenticationFilter;
import com.korit.springboot_study.security.filter.JwtAuthenticationFilter;
import com.korit.springboot_study.security.oauth2.OAuth2Service;
import com.korit.springboot_study.security.oauth2.OAuth2SuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private OAuth2SuccessHandler oAuth2SuccessHandler;

    @Autowired
    private OAuth2Service oAuth2Service;

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();
        http.httpBasic().disable();
        http.formLogin().disable();
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint);

        http.oauth2Login()
                .successHandler(oAuth2SuccessHandler)
                .userInfoEndpoint()
                .userService(oAuth2Service);

        http.authorizeRequests()
                .antMatchers(
                        "/swagger-ui/**",
                        "/v2/api-docs/**",
                        "/v3/api-docs/**",
                        "/swagger-resources/**",
                        "/server/hc"
                )
                .permitAll()
                .antMatchers("/api/auth/**")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/api/post/**")
                .permitAll()
                .anyRequest()
                .authenticated();
    }
}

/*
1. 클라이언트 요청
클라이언트가 API 엔드포인트, 예를 들어 /api/post/** 또는 /api/user/**와 같은 URL에 요청을 보냅니다.
이때 클라이언트는 HTTP 요청을 서버로 전송합니다. 요청이 SecurityConfig에서 정의된 경로에 포함된 경우, 서버는 다음과 같은 보안 흐름을 통해 요청을 처리합니다.

 2. CustomAuthenticationFilter 필터링
 서버는 클라이언트의 요청을 받으면, 먼저 CustomAuthenticationFilter 필터를 실행합니다. 이 필터는 HTTP 요청을 가로채고, 요청에 인증 정보를 처리합니다.
 익명 사용자 설정: CustomAuthenticationFilter는 요청이 들어오면, 익명 사용자(UserDetails) 객체를 생성합니다.
 여기서 **UserDetails**는 실제 인증된 사용자 정보가 아니며, 간단히 하드코딩된 값으로 가짜 사용자 정보를 생성합니다.

 getUsername()은 "aaaa", getPassword()는 "aaaa"로 하드코딩된 값을 사용합니다.
 getAuthorities()는 빈 권한 목록을 반환합니다. (이 부분은 실제로는 인증된 사용자의 권한 정보를 담을 것입니다.)
 UsernamePasswordAuthenticationToken 객체 생성: CustomAuthenticationFilter는 위에서 생성한 익명 사용자의 정보를 기반으로
 UsernamePasswordAuthenticationToken 객체를 생성합니다. 이 객체는 인증된 사용자 정보를 담고 있으며,
 이 객체를 **SecurityContext**에 설정하여 Spring Security가 요청을 처리할 수 있도록 합니다.


 SecurityContext 설정: SecurityContextHolder.getContext().setAuthentication(authentication) 호출을 통해
 생성된 인증 객체를 SecurityContext에 저장합니다. 이는 Spring Security가 현재 요청에 대한 인증 정보를 가지고 있을 수 있게 만듭니다.
 필터 체인 진행: 이후 filterChain.doFilter()를 호출하여, 필터 체인 내의 다음 필터로 요청을 전달합니다.

 3. 요청 처리
 필터링을 마친 후, 요청은 이제 보안 설정에 따라 처리됩니다. SecurityConfig에서 설정한 내용에 따라 다음과 같은 절차가 진행됩니다.
 URL 경로에 대한 접근 권한 처리:
 클라이언트가 요청한 URL이 /api/post/**, /api/user/**와 같은 공개된 URL일 경우, SecurityConfig에서 permitAll()로 설정한 대로
 인증 없이 해당 요청을 처리할 수 있습니다. 즉, 이 경로에 대해서는 인증 없이 접근이 허용됩니다.
 만약 요청한 URL이 permitAll()로 설정된 경로 외의 다른 경로라면, Spring Security는 요청을 인증된 사용자만 접근할 수 있도록 제한합니다.
 4. 인증되지 않은 사용자 처리
 만약 클라이언트가 인증되지 않은 상태로 인증이 필요한 경로를 요청한 경우, **CustomAuthenticationEntryPoint**가 처리합니다.
 이는 인증 오류를 처리하기 위한 클래스입니다.
 authenticationEntryPoint 호출: 클라이언트가 인증되지 않은 상태로 인증이 필요한 페이지나 API를 요청하면,
 Spring Security는 **CustomAuthenticationEntryPoint**를 호출합니다. 이 클래스는 인증 예외가 발생했을 때 실행됩니다.
 예외 처리: CustomAuthenticationEntryPoint.commence() 메서드는 인증되지 않은 사용자에게 403 Forbidden 상태 코드와 함께 오류 메시지를 JSON 형식으로 응답합니다.
 여기서는 "인증필요합니다!!!"라는 메시지를 반환하며, 클라이언트는 인증이 필요하다는 정보를 받게 됩니다.

 5. 응답 전송
 인증이 성공적으로 완료되면, 서버는 요청을 정상적으로 처리하고, 응답을 클라이언트에게 보냅니다.
 인증 실패시, CustomAuthenticationEntryPoint에서 403 오류와 함께 "인증필요합니다!!!"라는 메시지가 포함된 JSON 응답을 클라이언트로 보냅니다.
*/