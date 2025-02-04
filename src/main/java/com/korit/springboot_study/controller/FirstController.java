package com.korit.springboot_study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// html,Json가능
@Controller // controller라는 클래스를 만들면 @controller어노테이션 추가
public class FirstController {

    @GetMapping("/mvc/hello")
    public String hello(Model model) {
        model.addAttribute("name","김준일"); // 속성추가
        System.out.println("hello 메서드 호출");
        return "/resource/templates/hello.html"; // 파일명
    }

    @GetMapping("/mvc/hello2")
    public String hello2() {
        System.out.println("hello 메서드 호출");
        return "/resource/templates/hello2.html"; // 파일명
    }
}
