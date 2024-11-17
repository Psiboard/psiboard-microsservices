package com.psiboard.patients_service.core.application.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.psiboard.patients_service.adapters.out.messaging.MessagingProducerInterface;
import com.psiboard.patients_service.core.application.dto.SchedulingRequestDto;
import com.psiboard.patients_service.core.application.dto.SchedulingResponseDto;
import com.psiboard.patients_service.core.application.ports.in.SchedulingServiceInputPort;
import com.psiboard.patients_service.core.application.ports.out.SchedulingPersistencePort;

@Service
public class SchedulingServiceUseCases implements SchedulingServiceInputPort {

    private final SchedulingPersistencePort schedulingPersistencePort;
    private final MessagingProducerInterface messagingProducerInterface;

    public SchedulingServiceUseCases(SchedulingPersistencePort schedulingPersistencePort,
            MessagingProducerInterface messagingProducerInterface) {
        this.schedulingPersistencePort = schedulingPersistencePort;
        this.messagingProducerInterface = messagingProducerInterface;
    }

    @Override
    @Transactional
    public SchedulingResponseDto create(SchedulingRequestDto patient) {
        SchedulingResponseDto patientResponse = schedulingPersistencePort.create(patient);
        messagingProducerInterface.publishNotification(patient);
        return patientResponse;
    }

    @Override
    public List<SchedulingResponseDto> findAll() {
        return schedulingPersistencePort.findAll();
    }

    @Override
    public SchedulingResponseDto update(String id, SchedulingRequestDto patient) {
        return schedulingPersistencePort.update(id, patient);
    }

    @Override
    public void delete(String id) {
        schedulingPersistencePort.delete(id);
    }

    @Override
    public List<SchedulingResponseDto> findSchedules(String userId, LocalDate date) {
        return schedulingPersistencePort.findSchedules(userId, date);
    }

    @Override
    public List<String> findAvailableHours(LocalDate date, String userId) {
        return schedulingPersistencePort.findAvailableHours(date, userId);
    }

}
