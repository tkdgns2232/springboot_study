package com.korit.springboot_study.ioc_study;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class StudyB {
    @Bean
    public StudyD studyCall() {
        System.out.println("StudyB call");
        return new StudyD();
    }
    @Bean
    public StudyD studyCall2() {
        System.out.println("StudyB call2");
        return new StudyD();
    }
}
