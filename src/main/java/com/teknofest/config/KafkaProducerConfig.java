package com.teknofest.config;

import com.teknofest.exception.KafkaPublishException;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class KafkaProducerConfig {

    //Broker
    @Value("${kafka.broker}")
    private String BOOTSTRAP_SERVERS;

    @Value("${kafka.topic}")
    private String topicName;

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProps.put(
                ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 3000);
        configProps.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 3000);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    public Void publish(Object message) {
        try {
            kafkaTemplate().send(topicName, message);
        } catch (Exception e) {
            log.error("Kafka Producer Not Send Message!");
            throw new KafkaPublishException("Kafka Producer Not Send Message!");
        }

        log.debug("Kafka successfully sent the message!");
        
        return null;
    }

}
