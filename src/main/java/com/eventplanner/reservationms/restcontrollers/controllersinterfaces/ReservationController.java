package com.eventplanner.reservationms.restcontrollers.controllersinterfaces;

import com.eventplanner.reservationms.models.Reservation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



public interface ReservationController {

    @PostMapping(value = "/create",produces = {"application/json"},consumes = "application/json")
    ResponseEntity<?> createReservation(@Validated @RequestBody Reservation reservation);

}
