package com.korit.springboot_study.ioc;

import org.springframework.asm.ByteVector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 설정 객체 @Configuration일때 주로 @Bean사용
public class ConfigA {

    @Bean // 메소드명이 컨포먼트명이된다.지금은 call 만약 @Bean(value ="aaa")이면 aaa가 이름이된다.
    public ClassD call() { // 생성과 동시에 호출
        System.out.println("ConfigA call");
        return new ClassD();
    }
    // 이렇게 쓰는 경우는 2가지
    // 1. 생성자의 매개변수를 지정할때
    // 2. 라이브러리에 들어있는 객체를 생성해서 Bean에 등록하고 싶을때
    @Bean
    public ByteVector byteVector() {
        return new ByteVector();
    }
}
