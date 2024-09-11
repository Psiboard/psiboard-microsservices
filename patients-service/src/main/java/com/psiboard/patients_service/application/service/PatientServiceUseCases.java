package com.psiboard.patients_service.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.psiboard.patients_service.application.dto.PatientRequestDto;
import com.psiboard.patients_service.application.dto.PatientResponseDto;
import com.psiboard.patients_service.application.ports.in.PatientServiceInputPort;
import com.psiboard.patients_service.application.ports.out.PatientPersistencePort;

@Service
public class PatientServiceUseCases implements PatientServiceInputPort {

    private final PatientPersistencePort patientPersistencePort;

    public PatientServiceUseCases(PatientPersistencePort patientPersistencePort) {
        this.patientPersistencePort = patientPersistencePort;
    }

    @Override
    public PatientResponseDto create(PatientRequestDto patient) {
        return patientPersistencePort.create(patient);
    }

    @Override
    public List<PatientResponseDto> findAll() {
        return patientPersistencePort.findAll();
    }

    @Override
    public List<PatientResponseDto> findByUserId(String id) {
        return patientPersistencePort.findByUserId(id);
    }

}
