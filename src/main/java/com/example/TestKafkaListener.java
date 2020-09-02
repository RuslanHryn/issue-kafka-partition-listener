package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TestKafkaListener {

    @KafkaListener(id = "first", groupId = "crx", topicPartitions = @TopicPartition(topic = "TestTopic", partitions = "0"))
    public void handleFirstPartition(String message) {
        log.info("received partition 0: " + message);
    }

    @KafkaListener(id = "second", groupId = "crx", topicPartitions = @TopicPartition(topic = "TestTopic", partitions = "1"))
    public void handleSecondPartition(String message) {
        log.info("received partition 1: " + message);
    }

    @KafkaListener(topics = "AnotherTopic")
    public void handleAnotherTopic(String message) {
        log.info("received AnotherTopic: " + message);
    }
}
