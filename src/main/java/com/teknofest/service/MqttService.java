package com.teknofest.service;

import com.teknofest.config.MqttConfig;
import com.teknofest.dto.response.RouteResponseDto;
import com.teknofest.exception.MqttPublishException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MqttService {

    @Value("${mqtt.topic}")
    String topicName;

    @Value("${mqtt.qos}")
    Integer qos;

    @Value("${mqtt.retained}")
    Boolean retained;

    private final MqttConfig mqttConfig;

    @Getter
    private RouteResponseDto response = new RouteResponseDto();

    public void publishMessage(String message) {

        MqttMessage mqttMessage = new MqttMessage(message.getBytes());

        mqttMessage.setQos(qos);
        mqttMessage.setRetained(retained);

        try {

            mqttConfig.getInstance().publish(topicName, mqttMessage);
            log.info(message);
            response.setRoute(message);

        } catch (MqttException m){

            log.error("Mqtt Message Publish Exception!");
            throw new MqttPublishException("Mqtt Message Publish Exception!");

        }

    }

}
