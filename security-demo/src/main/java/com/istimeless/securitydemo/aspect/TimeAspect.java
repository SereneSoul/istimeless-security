package com.istimeless.securitydemo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author lijiayin
 */
@Aspect
@Component
public class TimeAspect {
    
//    @Around("execution(* com.istimeless.securitydemo.controller.UserController.*(..))")
//    public Object as(ProceedingJoinPoint pjp) throws Throwable {
//        System.out.println("time aspect start");
//        long start = System.currentTimeMillis();
//        Object[] args = pjp.getArgs();
//        Arrays.stream(args).forEach(arg -> System.out.println("arg is " + arg));
//        Object proceed = pjp.proceed();
//        long end = System.currentTimeMillis();
//        long cost = end - start;
//        System.out.println("time aspect cost:" + cost);
//        System.out.println("time aspect end");
//        return proceed;
//    }
}
