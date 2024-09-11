package com.psiboard.patients_service.application.ports.in;

import java.util.List;

import com.psiboard.patients_service.application.dto.SchedulingRequestDto;
import com.psiboard.patients_service.application.dto.SchedulingResponseDto;

public interface SchedulingServiceInputPort {
    SchedulingResponseDto create(SchedulingRequestDto patient);

    List<SchedulingResponseDto> findAll();
}
