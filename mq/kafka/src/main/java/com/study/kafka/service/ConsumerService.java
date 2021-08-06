/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.service;

import com.alibaba.fastjson.JSON;
import com.study.kafka.domain.UniformEvent;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

/**
 *
 * @author boyan
 * @version : ConsumerService.java, v 0.1 2021年07月15日 11:53 上午 boyan Exp $
 */
@EnableBinding(Sink.class)
public class ConsumerService {



    @StreamListener("input")
    public void consumer(Message<UniformEvent<Object>> message) {

        UniformEvent<Object> uniformEvent = message.getPayload();

        System.out.println("comsumer "+JSON.toJSONString(message));
    }

}