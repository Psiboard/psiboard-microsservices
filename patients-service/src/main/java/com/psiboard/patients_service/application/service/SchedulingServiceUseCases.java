package com.psiboard.patients_service.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.psiboard.patients_service.application.dto.SchedulingRequestDto;
import com.psiboard.patients_service.application.dto.SchedulingResponseDto;
import com.psiboard.patients_service.application.ports.in.SchedulingServiceInputPort;
import com.psiboard.patients_service.application.ports.out.SchedulingPersistencePort;

@Service
public class SchedulingServiceUseCases implements SchedulingServiceInputPort {

    private final SchedulingPersistencePort schedulingPersistencePort;

    public SchedulingServiceUseCases(SchedulingPersistencePort schedulingPersistencePort) {
        this.schedulingPersistencePort = schedulingPersistencePort;
    }

    @Override
    public SchedulingResponseDto create(SchedulingRequestDto patient) {
        return schedulingPersistencePort.create(patient);
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

    
}
