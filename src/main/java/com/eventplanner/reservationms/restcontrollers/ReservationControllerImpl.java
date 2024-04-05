package com.eventplanner.reservationms.restcontrollers;

import com.eventplanner.reservationms.dto.ReservationDTO;
import com.eventplanner.reservationms.dto.mappers.ReservationMapper;
import com.eventplanner.reservationms.models.Reservation;
import com.eventplanner.reservationms.restcontrollers.controllersinterfaces.ReservationController;
import com.eventplanner.reservationms.services.ReservationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event_planner/reservation")
@AllArgsConstructor
public class ReservationControllerImpl  implements ReservationController {


    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;

    @Override
    public ResponseEntity<?> createReservation(Reservation reservation) {
        Reservation reservationSaved=reservationService.createReservation(reservation);
        return new ResponseEntity<>(reservationMapper.convertReservationToDto(reservationSaved), HttpStatus.OK);
    }




}
