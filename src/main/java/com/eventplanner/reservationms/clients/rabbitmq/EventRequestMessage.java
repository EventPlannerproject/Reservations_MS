package com.eventplanner.reservationms.clients.rabbitmq;


import lombok.*;

@Builder
@Data
public class EventRequestMessage {
  private  Long eventId;
  private  Integer guestNumbers;
}
