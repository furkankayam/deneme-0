package com.teknofest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class KafkaPublishException extends RuntimeException{
    public KafkaPublishException(String message) {
        super(message);
    }
}
