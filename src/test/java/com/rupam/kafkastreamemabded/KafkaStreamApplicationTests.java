package com.rupam.kafkastreamemabded;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.test.context.EmbeddedKafka;

@SpringBootTest
@EmbeddedKafka(
		partitions = 1,topics = {"test-topic"}
)
class KafkaStreamApplicationTests {

	@Autowired
	StreamBridge streamBridge;

	@BeforeEach
	public void setup(@Autowired EmbeddedKafka embeddedKafka){

	}

	@Test
	void contextLoads() {

	}

}
