package com.study.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;

@SpringBootApplication
@EnableBinding({Source.class, Sink.class})
public class KafkaApplication {

    public static void main(String[] args) {
        //System.setProperty("java.security.auth.login.config", "classpath:kafka_auth.conf");
        System.setProperty("java.security.auth.login.config", "/Users/boyan/local/IdeaProjects/Owner/top/mq/kafka/src/main/resources/kafka_auth.conf");
        SpringApplication.run(KafkaApplication.class, args);
    }

}
