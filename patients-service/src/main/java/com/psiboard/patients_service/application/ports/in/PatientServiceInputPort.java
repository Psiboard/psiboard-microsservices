package com.psiboard.patients_service.application.ports.in;

import java.util.List;

import com.psiboard.patients_service.application.dto.PatientRequestDto;
import com.psiboard.patients_service.application.dto.PatientResponseDto;

public interface PatientServiceInputPort {
    PatientResponseDto create(PatientRequestDto patient);

    List<PatientResponseDto> findAll();
}
