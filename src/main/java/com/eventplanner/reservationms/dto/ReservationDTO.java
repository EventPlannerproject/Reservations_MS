package com.eventplanner.reservationms.dto;

import com.eventplanner.reservationms.models.PaymentMethods;
import com.eventplanner.reservationms.models.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationDTO {

    private LocalDate creationDate;

    private LocalDate modificationDate;

    private Status reservationStatus;

    private LocalDate reservationDate;

    /** The number of people attending the event under the reservation.*/
    private Integer guestsNumbers;



    private PaymentMethods paymentInformation;
}
