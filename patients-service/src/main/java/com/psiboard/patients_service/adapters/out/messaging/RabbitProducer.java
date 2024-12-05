package com.psiboard.patients_service.adapters.out.messaging;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.psiboard.patients_service.core.application.dto.SchedulingRequestDto;

@Component
public class RabbitProducer implements MessagingProducerInterface {

    final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final DiscoveryClient discoveryClient;

    @Value("${broker.queue.notification.name}")
    private String routingKey;

    @Value("${broker.queue.notification.dlq.name}")
    private String dlqRoutingKey;

    public RabbitProducer(RabbitTemplate rabbitTemplate, DiscoveryClient discoveryClient) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.discoveryClient = discoveryClient;
    }

    private boolean isNotificationServiceAvailable() {
        try {
            List<ServiceInstance> instances = discoveryClient.getInstances("notification-service");
            if (instances != null && !instances.isEmpty()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @CircuitBreaker(name = "notificationServiceCircuitBreaker", fallbackMethod = "sendToDLQ")
    public void publishNotification(SchedulingRequestDto schedulingRequestDto) {
        if (isNotificationServiceAvailable()) {
            System.out.println("Mensagem produzida: " + schedulingRequestDto);
            rabbitTemplate.convertAndSend(routingKey, schedulingRequestDto);
        } else {
            sendToDLQ(schedulingRequestDto, new RuntimeException("NotificationService está fora do ar"));
        }
    }

    public void sendToDLQ(SchedulingRequestDto schedulingRequestDto, Throwable t) {
        System.out
                .println("NotificationService está fora do ar. Enviando mensagem para a DLQ: " + schedulingRequestDto);
        rabbitTemplate.convertAndSend(dlqRoutingKey, schedulingRequestDto);
    }
}
