package com.eventplanner.reservationms.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "event-service",url = "${application.config.events-url}")
public interface EventClient {

    @GetMapping(value = "/{eventId}")
    Integer  findAvailableCapacity(@PathVariable("eventId") Long eventId);

}
