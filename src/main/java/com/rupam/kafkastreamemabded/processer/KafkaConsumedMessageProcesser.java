package com.rupam.kafkastreamemabded.processer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rupam.kafkastreamemabded.module.CustomMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Supplier;

@Configuration
@Slf4j
public class KafkaConsumedMessageProcesser {

    @Autowired
    @Qualifier("obMapper")
    Supplier<ObjectMapper> objectMapperSupplier;

    public Supplier<Message<?>> messageProcesser(Message<?> input) {

        //if (input.getPayload() instanceof Byte)
        {
            processingRequest(input);

        }
        return () -> input;

    }

    private void processingRequest(Message<?> input) {
        try {

            ObjectMapper mapper = objectMapperSupplier.get();
            log.info("header info {}",input.getHeaders().get("deliveryAttempt"));
            CustomMessage cs = mapper.readValue((byte[]) input.getPayload(), CustomMessage.class);
            log.info("Data received {}", cs);
        } catch (IOException e) {
            log.info(" Error on consumption", e);
        }
    }
}
