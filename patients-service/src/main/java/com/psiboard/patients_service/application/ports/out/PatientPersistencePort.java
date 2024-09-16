package com.psiboard.patients_service.application.ports.out;

import java.util.List;

import com.psiboard.patients_service.application.dto.PatientRequestDto;
import com.psiboard.patients_service.application.dto.PatientResponseDto;
import com.psiboard.patients_service.application.dto.UpdatePatientRequestDto;

public interface PatientPersistencePort {

    PatientResponseDto create(PatientRequestDto patient);
    
    List<PatientResponseDto> findAll();

    List<PatientResponseDto> findByUserId(String id);

    PatientResponseDto update(String id, UpdatePatientRequestDto patient);

    void delete(String id);
}
