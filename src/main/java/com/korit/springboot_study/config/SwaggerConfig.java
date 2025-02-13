package com.korit.springboot_study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                // 해당 패키지 안에 있는 모듈 컨트롤러 스웨거 적용
                .apis(RequestHandlerSelectors.basePackage("com.korit.springboot_study.controller"))
                // 모든 URL 스웨어 적용
                .paths(PathSelectors.any()) // 모든 주소 url
                .build()
                .apiInfo(getapiInfo())
                .securitySchemes(Arrays.asList(getApikey()))
                .securityContexts(Arrays.asList(getSecurityContext()));
    }

    private ApiInfo getapiInfo() {
        return new ApiInfoBuilder() // ApiInfoBuilder를 사용해 API 정보를 빌드
                .title("API 문서 제목") // 문서 제목 설정
                .description("API 문서 설명") // 문서 설명 설정
                .version("1.0") // API 버전 설정
                .contact(new Contact("관리자", "주소", "이메일")) // 연락처 정보 설정
                .build(); // ApiInfo 객체 생성
    }

    private ApiKey getApikey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext getSecurityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }


}
