package com.psiboard.patients_service.framework.helpers;

import com.psiboard.patients_service.application.dto.PatientResponseDto;
import com.psiboard.patients_service.domain.Patient;

public class Utils {
    public static PatientResponseDto convertToPatientResponseDto(Patient patientDetails) {
        if (patientDetails instanceof Patient) {
            Patient patient = (Patient) patientDetails;
            return new PatientResponseDto(patient.getId(), patient.getName(), patient.getAge(), patient.getEmail(),
                    patient.getPhone(), patient.getStreet(), patient.getDistrict(), patient.getCity(),
                    patient.getState(), patient.getZip_code(), patient.getAdditional_info(), patient.getUser_id());
        }
        return null; // Tratar exceção adequadamente
    }
}
