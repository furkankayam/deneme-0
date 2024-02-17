package com.teknofest.controller;

import com.teknofest.dto.request.RouteRequestDto;
import com.teknofest.dto.response.RouteResponseDto;
import com.teknofest.service.KafkaProducerService;
import com.teknofest.service.MqttService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Route")
public class RouteController {

    private final KafkaProducerService kafkaProducerService;

    private final MqttService mqttService;

    @Operation(
            description = "Sending a route",
            summary = "Sending a route to the robot",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400"
                    )
            }
    )
    //REQUEST
    @PostMapping("/request")
    public ResponseEntity<Boolean> routeRequest(@Valid @RequestBody RouteRequestDto routeRequestDto){
        return new ResponseEntity<>(kafkaProducerService.sendRequest(routeRequestDto), HttpStatus.OK);
    }

    @Operation(
            description = "Get route",
            summary = "Returning the approved route from the backend",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404"
                    )
            }
    )
    //RESPONSE
    @GetMapping("/response")
    public ResponseEntity<RouteResponseDto> routeResponse(){
        return new ResponseEntity<>(mqttService.getResponse(), HttpStatus.OK);
    }

}
