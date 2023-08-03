package com.chien.demoPerson.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PersonServiceAspect {

  private Logger logger = LoggerFactory.getLogger(PersonServiceAspect.class);

  @Pointcut("execution(* com.chien.demoPerson.service.*.*(..))")
  private void myPointcut() {}

  @AfterThrowing(value = "myPointcut()", throwing = "ex")
  public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex) {
    System.out.println("After Throwing method: " + joinPoint.getSignature());
    System.out.println("After Throwing method (ex): " + ex.getMessage());
  }

//  @Around("execution(* com.chien.demoPerson.service.*.*(..))")
//  public void around(ProceedingJoinPoint joinPoint) throws Throwable {
//    Long startTime = System.currentTimeMillis();
//    logger.info("Start Time Taken by {} is {}", joinPoint, startTime);
//    joinPoint.proceed();
//    Long timeTaken = System.currentTimeMillis() - startTime;
//    logger.info("Time Taken by {} is {}", joinPoint, timeTaken);
//
//  }

  @Around("@annotation(com.chien.demoPerson.aspect.TrackTime)")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    Long startTime = System.currentTimeMillis();
    logger.info("Start Time Taken by {} is {}", joinPoint, startTime);
    Object result = joinPoint.proceed();
    Long timeTaken = System.currentTimeMillis() - startTime;
    logger.info("Time Taken by {} is {}", joinPoint, timeTaken);
    return result;
  }
}
