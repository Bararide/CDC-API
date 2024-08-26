package com.example.components;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaAuditor {

    @KafkaListener(topics = "test", groupId = "test")
    void listener(Object data) {
        if (data instanceof String) {
            log.info("Received message [{}] as String in test", data);
        } else {
            log.info("Received message [{}] as JSON in test", data);
        }
    }
}