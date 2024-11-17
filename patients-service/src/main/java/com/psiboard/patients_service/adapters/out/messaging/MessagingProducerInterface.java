package com.psiboard.patients_service.adapters.out.messaging;

import com.psiboard.patients_service.core.application.dto.SchedulingRequestDto;

public interface MessagingProducerInterface {
    void publishNotification(SchedulingRequestDto schedulingRequestDto);
}
