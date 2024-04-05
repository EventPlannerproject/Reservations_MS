package com.eventplanner.reservationms.services;

import com.eventplanner.reservationms.models.Reservation;
import com.eventplanner.reservationms.models.Status;

import java.util.List;
import java.util.Optional;

public interface ReservationService {

    Reservation createReservation(Reservation reservation);

    Reservation getReservationbyID(Long idReservation);

    Reservation updateReservationDetails(Long reservationId,Reservation reservation);

    Reservation cancelReservation(Long idReservation);

    List<Reservation>  getAllReservations ();


    List<Reservation> getUserHistoryReservations(Long userId);

    List<Reservation> getEventReservations(Long eventId);

    void deleteReservationById(Long idReservation);

    Integer countNumbersEventReservations(Long eventId);


    Reservation updateReservationStatus(Status status,Long idReservation);



    //Search Reservations by Criteria
    //Get User Details for Reservation

}
