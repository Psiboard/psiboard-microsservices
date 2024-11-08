package com.psiboard.patients_service.core.application.ports.in;

import java.util.List;

import com.psiboard.patients_service.core.application.dto.SchedulingRequestDto;
import com.psiboard.patients_service.core.application.dto.SchedulingResponseDto;

import java.time.LocalDate;

public interface SchedulingServiceInputPort {
    SchedulingResponseDto create(SchedulingRequestDto patient);

    List<SchedulingResponseDto> findAll();

    SchedulingResponseDto update(String id, SchedulingRequestDto scheduling);

    void delete(String id);

    List<SchedulingResponseDto> findSchedules(String userId, LocalDate date);

    List<String> findAvailableHours(LocalDate date, String userId);
}
