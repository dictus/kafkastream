package com.rupam.kafkastreamemabded.kafkaconfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Function;

@Configuration
@Slf4j
public class PublisherListenerConfig {




    @Bean
    public Function<Message<?>,Message<?>> messageConsumer() {
        return input ->
        {
            log.info("consumedd {}",input);

            return input;
        };
    }
}
