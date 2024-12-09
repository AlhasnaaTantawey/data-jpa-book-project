package com.global.book.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;


@Component
@Aspect
@Order(1)
public class LogTimeAspect {

    private final Logger log = LoggerFactory.getLogger(LogTimeAspect.class);


    @Pointcut(value = "execution(* com.global.book.repository..*(..)) ")
    public  void forRepostioryLog(){

    }

    @Pointcut(value = "execution(* com.global.book.service.*.*(..)) ")
    public  void forServiceLog(){}

    @Pointcut(value = "execution(* com.global.book.controller..*(..)) ")
    public  void forControllerLog(){}

    @Pointcut(value = "forControllerLog() || forServiceLog() || forRepostioryLog()")
    public  void forAllApp(){}

 // @Before(value = "forAllApp()")
  //@After(value = "forAllApp()")
  @AfterReturning(value = "forAllApp()")
    public  void beforeMethod(JoinPoint joinPoint){
        String nameMethod=  joinPoint.getSignature().toShortString();

        log.info("======> Method name is >> {}"+ nameMethod);
       Object[] args=    joinPoint.getArgs();
        for ( Object arg: args) {
            log.info("=======> arguments =" + arg);
        }

    }

}
