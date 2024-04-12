package com.eventplanner.reservationms.clients.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EventResponse {

    private String title;
    private String description;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private String organizer;
    private String mailAddressOfOrganizer;
    private Integer numberOfAttendees;
    private Integer numbersOfParticipants;
    private String status;
    private String type;
    private String summary;
}
