package com.example.demo;

import com.example.DemoApplication;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;

@EmbeddedKafka(
        brokerProperties = {
                "transaction.state.log.replication.factor=1",
                "transaction.state.log.min.isr=1"
        }
)
@SpringBootTest(classes = DemoApplication.class)
class DemoApplicationTests {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    void testListeners() throws InterruptedException {

        ProducerRecord<String, String> firstRecord = new ProducerRecord<>("TestTopic", 0, null, "zero");
        kafkaTemplate.send(firstRecord);

        ProducerRecord<String, String> secondRecord = new ProducerRecord<>("TestTopic", 1, null, "one");
        kafkaTemplate.send(secondRecord);

        Thread.sleep(10000);
    }
}
