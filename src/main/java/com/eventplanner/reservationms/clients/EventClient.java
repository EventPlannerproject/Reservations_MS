package com.eventplanner.reservationms.clients;


import com.eventplanner.reservationms.clients.responses.EventResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@FeignClient(name = "event-service",url = "${events-service-url}")
public interface EventClient {

    @GetMapping(value = "/{eventId}")
    Integer  findAvailableCapacity(@PathVariable("eventId") Long eventId);

    @GetMapping(value = "/retrieveEvent/{idEvent}")
    Optional<EventResponse> retrieveEvent(@PathVariable Integer idEvent);

}
