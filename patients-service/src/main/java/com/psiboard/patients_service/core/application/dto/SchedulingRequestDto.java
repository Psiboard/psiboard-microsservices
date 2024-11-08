package com.psiboard.patients_service.core.application.dto;

import java.time.LocalDate;

import com.psiboard.patients_service.core.domain.SchedulingType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchedulingRequestDto {

    private LocalDate date;
    private String hour;
    private SchedulingType type;
    private String user_id;
    private String patient_id;

}
