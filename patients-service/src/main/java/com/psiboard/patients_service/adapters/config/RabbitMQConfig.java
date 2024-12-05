package com.psiboard.patients_service.adapters.config;

import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${broker.queue.notification.name}")
    private String queueName;

    @Value("${broker.queue.notification.dlq.name}")
    private String dlqName;

    @Value("${broker.exchange.name}")
    private String exchangeName;

     @Bean
    Queue queue() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-dead-letter-exchange", exchangeName);
        args.put("x-dead-letter-routing-key", dlqName);
        return new Queue(queueName, true, false, false, args);
    }

    @Bean
    Queue deadLetterQueue() {
        return new Queue(dlqName, true);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(queueName);
    }

    @Bean
    Binding dlqBinding(Queue deadLetterQueue, TopicExchange exchange) {
        return BindingBuilder.bind(deadLetterQueue).to(exchange).with(dlqName);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
