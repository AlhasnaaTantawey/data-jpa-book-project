package com.global.book.aspect;

import org.apache.tomcat.util.buf.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
@Aspect
public class LogTimeAspect {

    private  Logger log = LoggerFactory.getLogger(LogTimeAspect.class);

//    @Around(value = "execution(* com.global.book.service.*.*(..)) && !within(LogTimeAspect)")
//    public  Object logTime(ProceedingJoinPoint joinPoint) throws Throwable {
//
//      long startTime =  System.currentTimeMillis();
//      StringBuilder builder =new StringBuilder("KPI");
//      builder.append("[").append(joinPoint.getKind()).append("]\tfor: ").append(joinPoint.getSignature())
//              .append("\twith args: ").append("(").append(Arrays.stream(joinPoint.getArgs()).map(  )+ ")");
//      builder.append("\ttook: ");
//        Object result = joinPoint.proceed();
//
//    log.info( "aop "+builder.append(System.currentTimeMillis() - startTime) .append("ms").toString());
//    return  result;
//    }

//    @Around(value = "execution(* com.global.book.service.*.*(..))")
//    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
//        long startTime = System.currentTimeMillis();
//
//        Object result = joinPoint.proceed();  // Proceed with method execution
//
//        long timeTaken = System.currentTimeMillis() - startTime;
//        String methodName = joinPoint.getSignature().getName();
//
//       log.info("Execution time of " + methodName + ": " + timeTaken + " ms");
//
//        return result;  // Return the original result of the method
//    }

}
