package com.psiboard.notification_service.app.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.psiboard.notification_service.app.dto.SchedulingDto;

@Component
public class RabbitMQConsumer {

    @RabbitListener(queues = "${broker.queue.notification.dlq.name}")
    public void receiveMessageFromDLQ(SchedulingDto sdSchedulingMessage) {
        System.out.println("Mensagem recebida da DLQ e desserializada: " + sdSchedulingMessage.toString());
        // Processar a mensagem da DLQ
    }

    @RabbitListener(queues = "${broker.queue.notification.name}")
    public void receiveMessage(SchedulingDto sdSchedulingMessage) {
        System.out.println("Mensagem recebida e desserializada: " + sdSchedulingMessage.toString());
        // Processar a mensagem da fila principal
    }
}
