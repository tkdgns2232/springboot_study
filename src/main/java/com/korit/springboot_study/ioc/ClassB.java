package com.korit.springboot_study.ioc;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ClassB {
    @Qualifier(value = "c1") // 변수명이 다르면 사용
    @Autowired
    private ClassC c1;
    @Qualifier(value = "c2") // 변수명이 다르면 사용
    @Autowired
    private ClassC c2;

    public void classCallB() {
        System.out.println("ClassB 메소드 호출");
    }
}
