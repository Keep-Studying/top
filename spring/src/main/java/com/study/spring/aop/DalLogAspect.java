/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 *
 * @author boyan
 * @version : LogAspect.java, v 0.1 2021年07月15日 12:10 上午 boyan Exp $
 */
@Aspect
@Order
@Component
public class DalLogAspect {

    //execution(* com.jiuxian..service.*.*(..))
    @Pointcut("execution(* com.study..aop.*.*(..) )")
    public void pointCut(){}

    @Before(value = "pointCut()")
    public void methodBefore(JoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("执行目标方法【"+methodName+"】的<前置通知>,入参"+ Arrays.asList(joinPoint.getArgs()));
    }

    @After(value = "pointCut()")
    public void methodAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("执行目标方法【"+methodName+"】的<后置通知>,入参"+Arrays.asList(joinPoint.getArgs()));
    }

    @AfterReturning(value = "pointCut()",returning = "result")
    public void methodReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("执行目标方法【"+methodName+"】的<返回通知>,入参"+Arrays.asList(joinPoint.getArgs()));
    }

    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            System.out.println("around");
            String clazzName = joinPoint.getSignature().getClass().getSimpleName();
            String methodName = joinPoint.getSignature().getName();
            Object[] args = joinPoint.getArgs();
            Object res = joinPoint.proceed();
            System.out.println("执行目标方法【"+clazzName+"."+methodName+"】的<环绕通知>,入参"+Arrays.asList(args)+"，result："+res);
            return res;
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        } finally {
            System.out.println("around");
        }
    }

    @AfterThrowing(value = "pointCut()")
    public void methodAfterThrowing(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("执行目标方法【"+methodName+"】的<异常通知>,入参"+Arrays.asList(joinPoint.getArgs()));
    }
}