package com.global.book.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Order(0)
@Aspect
public class MeasurementAspect {
    private final Logger log = LoggerFactory.getLogger(MeasurementAspect.class);

    @Around(value = "execution(* com.global.book.service.*.*(..)) ")
    public  Object logTime(ProceedingJoinPoint joinPoint) throws Throwable {

      long startTime =  System.currentTimeMillis();
      StringBuilder builder =new StringBuilder("KPI");
      builder.append("[").append(joinPoint.getKind()).append("]\tfor: ").append(joinPoint.getSignature())
              .append("\twith args: ").append("(").append(StringUtils.arrayToDelimitedString(joinPoint.getArgs(), ", ")).append(")");
      builder.append("\ttook: ");
        Object result = joinPoint.proceed();

    log.info( "aop "+builder.append(System.currentTimeMillis() - startTime) .append("ms"));
    return  result;
    }
}
