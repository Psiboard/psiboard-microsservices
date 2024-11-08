package com.psiboard.patients_service.core.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.psiboard.patients_service.core.application.dto.PatientRequestDto;
import com.psiboard.patients_service.core.application.dto.PatientResponseDto;
import com.psiboard.patients_service.core.application.dto.UpdatePatientRequestDto;
import com.psiboard.patients_service.core.application.ports.in.PatientServiceInputPort;
import com.psiboard.patients_service.core.application.ports.out.PatientPersistencePort;

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

    @Override
    public PatientResponseDto update(String id, UpdatePatientRequestDto patient) {
        return patientPersistencePort.update(id, patient);
    }

    @Override
    public void delete(String id) {
        patientPersistencePort.delete(id);
    }

    @Override
    public List<PatientResponseDto> findPatientByName(String name) {
        return patientPersistencePort.findPatientByName(name);
    }

    @Override
    public PatientResponseDto findOne(String id) {
        return patientPersistencePort.findOne(id);
    }

}
