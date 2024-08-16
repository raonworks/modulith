package com.example.modular.orders;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderIntegrationConfiguration {
    public static final String ORDER_DESTINATION = "orders";

    @Bean
    Binding binding(Queue queue, Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ORDER_DESTINATION).noargs();
    }

    @Bean
    Queue queue() {
        return QueueBuilder.durable(ORDER_DESTINATION).build();
    }

    @Bean
    Exchange exchange() {
        return ExchangeBuilder.topicExchange(ORDER_DESTINATION).build();
    }

}
