package com.teknofest.service;

import com.teknofest.dto.request.RouteRequestDto;
import com.teknofest.exception.KafkaConsumerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final MqttService mqttService;

    private static final String TOPIC_NAME = "route";
    private static final String GROUP_ID = "KafkaRouteConsumer-GroupId";

    @KafkaListener(topics = {TOPIC_NAME}, groupId = GROUP_ID, containerFactory = "kafkaListenerContainerFactory")
    public void listener(@Payload RouteRequestDto requestDto, ConsumerRecord c) {
        try {
            log.info((String) c.value());
            mqttService.publishMessage((String) c.value());
        } catch (Exception e) {
            log.error("Kafka Consumer Null!");
            throw new KafkaConsumerException("Kafka Consumer Null!");
        }
    }

}
