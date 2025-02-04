package com.korit.springboot_study.ioc_study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudyA {

    @Autowired
    private StudyD studyD;

    public void studyD() {
        System.out.println("studyA 호출");
        studyD.StudyD();
    }
}
