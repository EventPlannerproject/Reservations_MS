package com.eventplanner.reservationms.payload.emailsenderconfig;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailsDetails {

    private String recipient;

    private String messageBody;

    private String subject;




}
