package com.teknofest.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {DataWriteOrReadException.class})
    public ResponseEntity<Object> handleDataWriteOrReadException(DataWriteOrReadException e) {
        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;

        ExceptionMessage apiException = new ExceptionMessage(
            e.getMessage(),
                internalServerError,
            ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, internalServerError);
    }

    @ExceptionHandler(value = {KafkaConsumerException.class})
    public ResponseEntity<Object> handleKafkaConsumerException(KafkaConsumerException e) {
        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;

        ExceptionMessage apiException = new ExceptionMessage(
                e.getMessage(),
                internalServerError,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, internalServerError);
    }

    @ExceptionHandler(value = {KafkaPublishException.class})
    public ResponseEntity<Object> handleKafkaPublishException(KafkaPublishException e) {
        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;

        ExceptionMessage apiException = new ExceptionMessage(
                e.getMessage(),
                internalServerError,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, internalServerError);
    }

    @ExceptionHandler(value = {MqttConnectException.class})
    public ResponseEntity<Object> handleMqttConnectException(MqttConnectException e) {
        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;

        ExceptionMessage apiException = new ExceptionMessage(
                e.getMessage(),
                internalServerError,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, internalServerError);
    }

    @ExceptionHandler(value = {MqttPublishException.class})
    public ResponseEntity<Object> handleMqttPublishException(MqttPublishException e) {
        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;

        ExceptionMessage apiException = new ExceptionMessage(
                e.getMessage(),
                internalServerError,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, internalServerError);
    }

}
