package com.teknofest.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ExceptionMessage {

    //Fields
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

    //Construction
    public ExceptionMessage(String message,
                            HttpStatus httpStatus,
                            ZonedDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    //Getters
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}
