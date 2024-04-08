package com.eventplanner.reservationms.restcontrollers;

import com.eventplanner.reservationms.dto.ReservationDTO;
import com.eventplanner.reservationms.dto.mappers.ReservationMapper;
import com.eventplanner.reservationms.models.Reservation;
import com.eventplanner.reservationms.models.Status;
import com.eventplanner.reservationms.payload.ErrorResponse;
import com.eventplanner.reservationms.restcontrollers.controllersinterfaces.ReservationController;
import com.eventplanner.reservationms.services.ReservationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/event_planner/reservation")
@RequiredArgsConstructor
public class ReservationControllerImpl  implements ReservationController {


    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;

    @Override
    public ResponseEntity<?> createReservation(Reservation reservation) {
        Reservation reservationSaved=reservationService.createReservation(reservation);
        return new ResponseEntity<>(reservationMapper.convertReservationToDto(reservationSaved), HttpStatus.OK);
    }

    @Override
    public ReservationDTO getReservationById(Long id) {
        return reservationMapper.convertReservationToDto(reservationService.getReservationbyID(id));
    }

    @Override
    public ReservationDTO updateReservationDetails(Long reservationId, Reservation reservation) {
        return reservationMapper.convertReservationToDto(reservationService.updateReservationDetails(reservationId,reservation));
    }

    @Override
    public ResponseEntity<?> cancelReservation(Long reservationId) {
        Reservation reservation =reservationService.cancelReservation(reservationId);
        return new ResponseEntity<>("Reservation canceled Succeffully",HttpStatus.OK);
    }

    @Override
    public List<ReservationDTO> getAllReservations() {
        return reservationMapper.convertReservationsToListOfDto(reservationService.getAllReservations());
    }

    @Override
    public List<ReservationDTO> getUserHistoryReservations(Long userId) {
        return reservationMapper.convertReservationsToListOfDto(reservationService.getUserHistoryReservations(userId));
    }

    @Override
    public ReservationDTO getEventReservations(Long eventId) {
        return reservationMapper.convertReservationToDto(reservationService.getReservationbyID(eventId));
    }

    @Override
    public ResponseEntity<?> deleteReservation(Long id) {
        reservationService.deleteReservationById(id);
        return new ResponseEntity<>("reservation deleted successfully ",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> countNumbersEventReservations(Long eventId) {
       Integer numberOfReservations= reservationService.countNumbersEventReservations(eventId);
        return new ResponseEntity<>(numberOfReservations,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateReservationStatus(Long reservationId, String status) {
        if(isValidStatus(status)){
            reservationService.updateReservationStatus(Status.valueOf(status),reservationId);
            return new ResponseEntity<>("Status of reservation updated succeffuly",HttpStatus.OK);
        }
        return  new ResponseEntity<>(new ErrorResponse(50,"request error occured ","there is an error in your request please check the validity of your request"),HttpStatus.BAD_REQUEST);

    }

    private boolean isValidStatus(String status) {
        for (Status validStatus : Status.values()) {
            if (validStatus.equals(status)) {
                return true;
            }
        }
        return false;
    }

}
