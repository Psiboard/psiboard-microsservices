package com.psiboard.patients_service.application.ports.in;

import java.util.List;

import com.psiboard.patients_service.application.dto.PatientRequestDto;
import com.psiboard.patients_service.application.dto.PatientResponseDto;
import com.psiboard.patients_service.application.dto.UpdatePatientRequestDto;

public interface PatientServiceInputPort {
    PatientResponseDto create(PatientRequestDto patient);

    List<PatientResponseDto> findAll();

    List<PatientResponseDto> findByUserId(String id);

    List<PatientResponseDto> findPatientByName(String name);

    PatientResponseDto update(String id, UpdatePatientRequestDto patient);

    void delete(String id);
}
