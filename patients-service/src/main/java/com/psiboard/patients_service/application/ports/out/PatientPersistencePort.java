package com.psiboard.patients_service.application.ports.out;

import java.util.List;

import com.psiboard.patients_service.application.dto.PatientRequestDto;
import com.psiboard.patients_service.application.dto.PatientResponseDto;

public interface PatientPersistencePort {

    PatientResponseDto create(PatientRequestDto patient);
    List<PatientResponseDto> findAll();
}
