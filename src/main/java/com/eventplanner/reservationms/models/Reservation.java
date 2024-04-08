package com.eventplanner.reservationms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Reservation {

    /**
     * a reservation is created by a user for a specific date  of an Event
     * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate creationDate;

    private LocalDate modificationDate;

    private  Status reservationStatus;

    private LocalDate reservationDate;


    /** The number of people attending the event under the reservation.*/

    private Integer guestsNumbers;



    private PaymentMethods paymentInformation;

    private Long idUser;

    private Long idEvent;
}
