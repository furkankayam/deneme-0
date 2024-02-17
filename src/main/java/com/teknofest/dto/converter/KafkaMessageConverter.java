package com.teknofest.dto.converter;

import com.teknofest.dto.request.RouteRequestDto;
import com.teknofest.model.Route;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageConverter {

    public Route toRoute(RouteRequestDto routeRequestDto){
        Route route = new Route();
        route.setRoute(routeRequestDto.getRoute());
        return route;
    }

}
