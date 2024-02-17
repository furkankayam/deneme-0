package com.teknofest.service;

import com.teknofest.config.KafkaProducerConfig;
import com.teknofest.dto.request.RouteRequestDto;
import com.teknofest.dto.converter.KafkaMessageConverter;
import com.teknofest.exception.DataWriteOrReadException;
import com.teknofest.exception.KafkaPublishException;
import com.teknofest.model.Route;
import com.teknofest.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaProducerConfig kafkaProducerConfig;
    private final KafkaMessageConverter kafkaMessageConverter;
    private final RouteRepository routeRepository;

    public Boolean sendRequest(RouteRequestDto routeRequestDto) {

        boolean response = false;

        try {

            Route route = kafkaMessageConverter.toRoute(routeRequestDto);

            try {
                routeRepository.save(route);
                log.debug("Message Was Written Tt Database!");
            }catch (Exception e){
                log.error("The message could not be written to the database.");
                throw new DataWriteOrReadException("The message could not be written to the database.");
            }

            kafkaProducerConfig.publish(routeRequestDto);
            response = true;

        } catch (Exception e) {
            throw new KafkaPublishException("Kafka Producer Not Send Message!");
        }

        return response;

    }

}
