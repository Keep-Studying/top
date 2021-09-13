/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import static com.study.kafka.domain.CommonConstants.SYSTEM_NAME;
import static com.study.kafka.domain.CommonConstants.THREAD_POOL_TASK_EXECUTOR;

/**
 * UtilConfig
 *
 * @author boyan
 * @version : UtilConfig.java, v 0.1 2021-09-01 00:24 boyan
 */
@Configuration
public class UtilConfig {

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setMaxPoolSize(50);
        threadPoolTaskExecutor.setQueueCapacity(1000);
        threadPoolTaskExecutor.setKeepAliveSeconds(5);
        threadPoolTaskExecutor.setThreadNamePrefix(SYSTEM_NAME + THREAD_POOL_TASK_EXECUTOR);
        return threadPoolTaskExecutor;
    }
}