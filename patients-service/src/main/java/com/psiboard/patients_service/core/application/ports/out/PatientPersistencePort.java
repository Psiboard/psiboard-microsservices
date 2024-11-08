package com.psiboard.patients_service.core.application.ports.out;

import java.util.List;

import com.psiboard.patients_service.core.application.dto.PatientRequestDto;
import com.psiboard.patients_service.core.application.dto.PatientResponseDto;
import com.psiboard.patients_service.core.application.dto.UpdatePatientRequestDto;

public interface PatientPersistencePort {

    PatientResponseDto create(PatientRequestDto patient);
    
    List<PatientResponseDto> findAll();

    List<PatientResponseDto> findByUserId(String id);

    List<PatientResponseDto> findPatientByName(String name);

    PatientResponseDto update(String id, UpdatePatientRequestDto patient);

    void delete(String id);

    PatientResponseDto findOne(String id);
}
