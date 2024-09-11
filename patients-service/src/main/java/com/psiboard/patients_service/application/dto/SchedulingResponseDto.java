package com.psiboard.patients_service.application.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.psiboard.patients_service.domain.SchedulingType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchedulingResponseDto {
    private String id;
    private LocalDate date;
    private String hour;
    private SchedulingType type;
    private UUID user_id;
    private String patient_id;
}
