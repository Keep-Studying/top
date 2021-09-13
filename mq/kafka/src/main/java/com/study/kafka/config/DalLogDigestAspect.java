/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.study.kafka.util.ConstructMsgUtil;

import static com.study.kafka.domain.CommonConstants.*;

/**
 * DalLogDigestAspect
 *
 * @author boyan
 * @version : DalLogDigestAspect.java, v 0.1 2021-09-01 00:16 boyan
 */
@Aspect
@Order
@Component
public class DalLogDigestAspect {

    /**
     * LOGGER
     */
    private static final Logger LOG = LoggerFactory.getLogger(DalLogDigestAspect.class);
    private static final Logger DAL_DIGEST_LOG = LoggerFactory.getLogger(MQ_DAL_DIGEST);

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Pointcut("execution(* com.study.kafka.daointerface.*.*(..) )")
    public void pointCut() {}

    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //日志开始时间
        long startTime = System.currentTimeMillis();
        //类名
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        //方法名
        String methodName = joinPoint.getSignature().getName();
        //方法参数列表
        Object[] args = joinPoint.getArgs();
        //是否处理成功
        boolean isSuccess = true;
        //拦截方法的执行结果
        Object result = null;
        try {
            result = joinPoint.proceed();
            return result;
        } catch (Throwable t) {
            //调用失败
            isSuccess = false;
            throw t;
        } finally {
            //确保任何情况下业务都能正常进行
            try {
                //耗时
                long elapseTime = System.currentTimeMillis() - startTime;
                threadPoolTaskExecutor.execute(new LogTask(className, methodName, isSuccess, elapseTime, result, args));
            } catch (Exception e) {
                LOG.error("DalLogDigestAspect print digest log error",e);
            }

        }
    }

    /**
     * 构造日志打印字符串
     *
     * @param className
     * @param methodName
     * @param isSuccess
     * @param elapseTime
     * @param arguments
     * @param result
     * @return
     */
    protected String constructLog(String className, String methodName, boolean isSuccess,
        long elapseTime, Object result, Object[] arguments) {

        StringBuilder sb = new StringBuilder();

        sb.append(LOG_PREFIX);
        sb.append(LOG_PARAM_PREFIX);
        sb.append(className);
        sb.append(LOG_SEP_POINT);
        sb.append(methodName);
        sb.append(LOG_SEP_COMMA);
        sb.append(isSuccess ? YES : NO);
        sb.append(LOG_SEP_COMMA);
        sb.append(elapseTime);
        sb.append(TIME_UNIT);
        sb.append(LOG_PARAM_SUFFIX);

        //添加服务调用参数信息
        sb.append(LOG_PARAM_PREFIX);
        sb.append(ConstructMsgUtil.constructMsg(arguments));
        sb.append(LOG_PARAM_SUFFIX);

        //添加服务调用结果信息
        sb.append(LOG_PARAM_PREFIX);
        sb.append(ConstructMsgUtil.constructMsg(result));
        sb.append(LOG_PARAM_SUFFIX);

        sb.append(LOG_SUFFIX);

        return sb.toString();
    }

    class LogTask implements Runnable {

        private String className;
        private String methodName;
        private boolean isSuccess;
        private long elapseTime;
        private Object result;
        private Object[] arguments;

        public LogTask(String className, String methodName, boolean isSuccess, long elapseTime, Object result,
            Object[] arguments) {
            super();
            this.className = className;
            this.methodName = methodName;
            this.isSuccess = isSuccess;
            this.elapseTime = elapseTime;
            this.result = result;
            this.arguments = arguments;
        }

        @Override
        public void run() {
            DAL_DIGEST_LOG.info(constructLog(className, methodName, isSuccess, elapseTime, result, arguments));
        }
    }
}