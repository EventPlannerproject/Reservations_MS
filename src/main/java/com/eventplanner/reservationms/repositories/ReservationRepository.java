package com.eventplanner.reservationms.repositories;

import com.eventplanner.reservationms.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findReservationsByIdUser(Long userId);

    List<Reservation> findReservationsByIdEvent(Long eventId);
}
