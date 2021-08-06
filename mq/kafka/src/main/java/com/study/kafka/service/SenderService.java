/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;

/**
 *
 * @author boyan
 * @version : SenderService.java, v 0.1 2021年07月15日 11:47 上午 boyan Exp $
 */
@EnableBinding(Source.class)
public class SenderService {

    @Autowired
    private Source source;

    public void sendMsg(Object msg) {
        source.output().send(MessageBuilder.withPayload(msg).build());

    }
}