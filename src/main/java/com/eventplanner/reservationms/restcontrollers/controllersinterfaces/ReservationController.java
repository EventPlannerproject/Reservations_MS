package com.eventplanner.reservationms.restcontrollers.controllersinterfaces;

import com.eventplanner.reservationms.dto.ReservationDTO;
import com.eventplanner.reservationms.models.Reservation;
import com.eventplanner.reservationms.models.Status;
import jakarta.ws.rs.QueryParam;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public interface ReservationController {

    @PostMapping(value = "/create", produces = {"application/json"}, consumes = "application/json")
    ResponseEntity<?> createReservation(@Validated @RequestBody Reservation reservation);

    @GetMapping(value = "/{id}", produces = "application/json")
    ReservationDTO getReservationById(@PathVariable("id") Long id);

    @PostMapping(value = "/update", produces = "application/json",consumes = "application/json")
    ReservationDTO updateReservationDetails(@RequestParam(name = "reservationId", required = false) Long reservationId, @RequestBody Reservation reservation);


    @PutMapping(value = "/cancel/{reservationId}", produces = "application/json")
    ResponseEntity<?> cancelReservation(@PathVariable("reservationId") Long reservationId);

    @GetMapping(value = "/all", produces = "application/json")
    List<ReservationDTO> getAllReservations();

    @GetMapping(value = "/userHistory", produces = "application/json")
    List<ReservationDTO> getUserHistoryReservations(@RequestParam(name = "userId") Long userId);

    @GetMapping(value = "/eventReservations", produces = "application/json")
    ReservationDTO getEventReservations(@RequestParam(name = "eventId") Long eventId);

    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    ResponseEntity<?>  deleteReservation(@PathVariable("id") Long id);

    @GetMapping(value = "/eventreservationsNumber", produces = "application/json")
    ResponseEntity<?>  countNumbersEventReservations(@RequestParam(name = "eventId") Long id);

    @PutMapping(value = "/updateStatus", produces = "application/json")
    ResponseEntity<?>  updateReservationStatus(@RequestParam(name = "reservationId") Long reservationId,@RequestParam(name = "status") String status);








}
