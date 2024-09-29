package com.psiboard.patients_service.framework.helpers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.UUID;

import com.psiboard.patients_service.application.dto.PatientResponseDto;
import com.psiboard.patients_service.application.exception.BusinessException;
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

    public static void validateHourFormat(String hour) {
        if (!hour.matches("^([01]\\d|2[0-3]):([0-5]\\d)$")) {
            throw new BusinessException("O formato da hora deve ser HH:mm");
        }
    }

    public static void validateHourRange(String hour) {
        try {
            LocalTime time = LocalTime.parse(hour);
            LocalTime start = LocalTime.of(8, 0);
            LocalTime end = LocalTime.of(21, 0);

            if (time.isBefore(start) || time.isAfter(end)) {
                throw new BusinessException("O horário deve estar entre 08:00 e 21:00");
            }
        } catch (DateTimeParseException e) {
            throw new BusinessException("O formato da hora deve ser HH:mm");
        }
    }
}
