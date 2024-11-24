package com.psiboard.patients_service.adapters.out.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.psiboard.patients_service.core.application.dto.SchedulingRequestDto;

@Component
public class RabbitProducer implements MessagingProducerInterface {

    final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Value("${broker.queue.notification.name}")
    private String routingKey;

    public RabbitProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void publishNotification(SchedulingRequestDto schedulingRequestDto) {
        System.out.println("Mensagem produzida: " + schedulingRequestDto);
        rabbitTemplate.convertAndSend(routingKey, schedulingRequestDto);

    }
}
