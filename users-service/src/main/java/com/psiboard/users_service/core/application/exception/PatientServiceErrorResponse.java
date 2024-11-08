package com.psiboard.users_service.core.application.exception;

import java.util.List;

import com.psiboard.users_service.core.application.dto.PatientResponseDto;

public class PatientServiceErrorResponse extends ErrorResponse {
    private List<PatientResponseDto> patients;

    public PatientServiceErrorResponse(String message, List<PatientResponseDto> patients) {
        super(message);
        this.patients = patients;
    }

    public List<PatientResponseDto> getPatients() {
        return patients;
    }

    public void setPatients(List<PatientResponseDto> patients) {
        this.patients = patients;
    }
}