package com.teknofest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class KafkaConsumerException extends RuntimeException{
    public KafkaConsumerException(String message) {
        super(message);
    }
}
