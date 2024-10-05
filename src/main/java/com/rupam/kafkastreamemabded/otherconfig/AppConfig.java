package com.rupam.kafkastreamemabded.otherconfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

@Configuration
public class AppConfig {

    @Bean("obMapper")
    public Supplier<ObjectMapper> objectMapperSupplier() {
        return () -> new ObjectMapper();
    }
}
