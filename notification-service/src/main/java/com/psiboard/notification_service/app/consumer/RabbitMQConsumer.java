package com.psiboard.notification_service.app.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.psiboard.notification_service.app.dto.SchedulingDto;

@Component
public class RabbitMQConsumer {

    @RabbitListener(queues = "${broker.queue.notification.name}")
    public void receiveMessage(SchedulingDto sdSchedulingMessage) {
        System.out.println("Mensagem recebida e desserializada: " + sdSchedulingMessage.toString());
    }
}
