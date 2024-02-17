package com.teknofest.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class MqttPublishModel {

    @NotNull
    @Value("${mqtt.topic}")
    private String topic;

    @NotNull
    private String message;

    @NotNull
    @Value("${mqtt.retained}")
    private Boolean retained;

    @NotNull
    @Value("${mqtt.qos}")
    private Integer qos;

}
