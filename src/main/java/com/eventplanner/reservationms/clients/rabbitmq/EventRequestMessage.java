package com.eventplanner.reservationms.clients.rabbitmq;


import lombok.*;

@Builder
@Data
public class EventRequestMessage {
  private  Integer eventId;
  private  Integer guestNumbers;
}
