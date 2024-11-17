package com.psiboard.patients_service.adapters.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${broker.queue.notification.name}")
    private String queue;

    @Bean
    Queue queue() {
        return new Queue(queue, true);
    }

}
