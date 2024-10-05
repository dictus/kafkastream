package com.rupam.kafkastreamemabded.kafkaconfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rupam.kafkastreamemabded.processer.KafkaConsumedMessageProcesser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
@Slf4j
public class PublisherListenerConfig {


    @Autowired
    KafkaConsumedMessageProcesser kafkaConsumedMessageProcesser;

    @Bean
    public Function<Message<?>, Message<?>> messageConsumer() {
        return input ->
        {
            log.info("consumed message  {} ", input.getPayload());
            kafkaConsumedMessageProcesser.messageProcesser(input);
            return input;
        };
    }


}
