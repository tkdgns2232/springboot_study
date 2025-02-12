package com.korit.springboot_study.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


//   Aspect	- 공통적인 기능들을 모듈화 한것을 의미합니다.

@Aspect // 해당 클래스를 Aspect로 사용하겠다는 것을 명시합니다.
@Component
// @Component 필요
public class TimerAspect {

    @Pointcut("execution(* com.korit.springboot_study.service.PostService.getPostById(..))")
    private void executionPointCut() {} // pointCut: Advice를 적용할 메소드의 범위를 지정하는 것을 의미합니다.

    @Pointcut("@annotation(com.korit.springboot_study.aspect.annotation.TimerAop)") //
    private void annotationPointCut() {}

    @Around("executionPointCut() || annotationPointCut()") // around: 대상 “메서드” 실행 전, 후 또는 예외 발생 시에 Advice를 실행합니다.
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable { // JoinPoint:Aspect가 적용될 수 있는 시점을 의미하며 메소드 실행 전, 후 등이 될 수 있습니다.
        StopWatch stopWatch = new StopWatch(); //StopWatch
        stopWatch.start();
        Object result = joinPoint.proceed(); // 합쳐질 지점.proceed는 실행 == joinPoint는 getPostByid의 메서드가 된다
        stopWatch.stop();
        System.out.println("메소드 실행시간: " + stopWatch.getTotalTimeMillis());
        return result;
    }

}
