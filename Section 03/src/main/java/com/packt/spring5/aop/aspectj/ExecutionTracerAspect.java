package com.packt.spring5.aop.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;

@Aspect
public class ExecutionTracerAspect {

  @Pointcut("execution(* com.packt.spring5.aop.*.*(..))")
  private void allMethods(){ }

  @Around("allMethods()")
  public Object trace(ProceedingJoinPoint pjp) throws Throwable {
    Object[] args = pjp.getArgs();
    String signature = pjp.getSignature().toString();
    Object retVal = pjp.proceed(args);
    System.out.println("Method '" + signature + "' was called with next arguments " + Arrays.toString(args));
    return retVal;
  }
}
