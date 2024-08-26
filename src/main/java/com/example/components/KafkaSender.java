package com.example.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage(String message, String topicName) {
        log.info("Sending : {}", message);
        log.info("--------------------------------");

        kafkaTemplate.send(topicName, message);
    }

    public <T> void sendJsonMessage(T object, String topicName) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(object);
            log.info("Sending JSON : {}", jsonMessage);
            log.info("--------------------------------");
            kafkaTemplate.send(topicName, jsonMessage);
        } catch (JsonProcessingException e) {
            log.error("Error converting object to JSON: ", e);
        }
    }
}