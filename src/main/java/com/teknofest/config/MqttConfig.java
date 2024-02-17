package com.teknofest.config;

import com.teknofest.exception.MqttConnectException;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Slf4j
@Configuration
public class MqttConfig {

    //Random Client ID : React CORS Failed
    private final String MQTT_PUBLISHER_ID = UUID.randomUUID().toString();

    @Value("${mqtt.config.url}")
    private String MQTT_SERVER_ADDRESS;

    //Request INSTANCE
    private static IMqttClient instance;

    @Bean
    public IMqttClient getInstance() {
        try {
            instance = new MqttClient(MQTT_SERVER_ADDRESS, MQTT_PUBLISHER_ID);

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);

            if (!instance.isConnected()) {
                instance.connect(options);
            }

        } catch (MqttException e) {
            log.error("MQTT Connect failed!");
            throw new MqttConnectException("MQTT Connect failed!");
        }

        log.debug("MQTT Connect Successful!");

        return instance;
    }

    public MqttConfig() {
    }
}
