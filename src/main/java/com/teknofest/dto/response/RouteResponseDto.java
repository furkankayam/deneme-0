package com.teknofest.dto.response;

import lombok.Data;
import java.time.LocalTime;

@Data
public class RouteResponseDto {

    private LocalTime timeZone = LocalTime.now();

    private String route;

}
