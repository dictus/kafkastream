package com.rupam.kafkastreamemabded;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rupam.kafkastreamemabded.module.CustomMessage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.MimeType;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@SpringBootTest
@EmbeddedKafka(
        partitions = 1, topics = {"test-topic"},
        bootstrapServersProperty = "spring.kafka.bootstrap-servers"
)
@ActiveProfiles("junit")
@Slf4j
class KafkaStreamApplicationTests {

    @Autowired
    StreamBridge streamBridge;

    @Autowired
    @Qualifier("obMapper")
    Supplier<ObjectMapper> objectMapperSupplier;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafka;

    @BeforeEach
    public void setup() throws JsonProcessingException {
        Map<String, Object> producerProp = KafkaTestUtils.producerProps(embeddedKafka);
        DefaultKafkaProducerFactory<String, Message<?>> defaultKafkaProducerFactory =
                new DefaultKafkaProducerFactory<>(producerProp);
        KafkaTemplate<String, Message<?>> kafkaTemplate = new KafkaTemplate<>(defaultKafkaProducerFactory, true);
        kafkaTemplate.setDefaultTopic("test-topic");


        CustomMessage cs1 = CustomMessage.builder().dream("test").slept(Boolean.TRUE).build();
        log.info(" Json ");
        streamBridge.send("messagePublisher-out-0",
                MessageBuilder.withPayload(objectMapperSupplier.get().writeValueAsString(cs1)).build());
    }

    @Test
    void contextLoads() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.await(8, TimeUnit.SECONDS);
//		countDownLatch.await(10)

    }

}
