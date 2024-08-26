package com.example.demo;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {

    @Bean
    public NewTopic test1() {
        return TopicBuilder.name("topic-1").build();
    }

    @Bean
    public NewTopic test2() {
        return TopicBuilder.name("topic-2").partitions(3).build();
    }
}
