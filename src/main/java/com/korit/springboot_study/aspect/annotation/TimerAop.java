package com.korit.springboot_study.aspect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// - Target: Aspect가 적용될 대상을 의미하며 메소드, 클래스 등이 이에 해당 됩니다.
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TimerAop {
}
