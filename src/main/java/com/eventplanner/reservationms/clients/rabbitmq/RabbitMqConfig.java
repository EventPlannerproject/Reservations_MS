package com.eventplanner.reservationms.clients.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbit mq instantiation
 * Queue Configuration
 * */


@Configuration
public class RabbitMqConfig {

    /** createQueue is to create Queue and define Topic */

    @Bean
    public Queue createQueue(){
        //topic
        return new Queue("reservationQueue");
    }

    /** mapStringToJSON is to map messages producer to be sent as JSON object */

    @Bean
    public MessageConverter mapStringToJSON(){
       return new Jackson2JsonMessageConverter();
    }


    @Bean
    public RabbitTemplate instantiateRabbitTemplate(final ConnectionFactory connectionFactory){
        var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(mapStringToJSON());
        return rabbitTemplate;
    }


}
