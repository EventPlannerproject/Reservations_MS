package com.eventplanner.reservationms.clients.rabbitmq;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationProducer {

    private final RabbitTemplate rabbitTemplate;


    public void  sendMessage(Integer numbersGuest,Long eventId){
       EventRequestMessage eventRequestMessage= EventRequestMessage.builder().eventId(eventId)
                       .guestNumbers(numbersGuest).build();
       rabbitTemplate.convertAndSend("reservationQueue",eventRequestMessage);
    }
}
