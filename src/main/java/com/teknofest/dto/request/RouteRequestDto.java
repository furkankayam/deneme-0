package com.teknofest.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RouteRequestDto {

    @NotNull
    private short[] route;

}
