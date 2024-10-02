package com.psiboard.patients_service.application.ports.in;

import java.util.List;
import java.time.LocalDate;

import com.psiboard.patients_service.application.dto.SchedulingRequestDto;
import com.psiboard.patients_service.application.dto.SchedulingResponseDto;

public interface SchedulingServiceInputPort {
    SchedulingResponseDto create(SchedulingRequestDto patient);

    List<SchedulingResponseDto> findAll();

    SchedulingResponseDto update(String id, SchedulingRequestDto scheduling);

    void delete(String id);

    List<SchedulingResponseDto> findSchedules(String userId, LocalDate date);

    List<String> findAvailableHours(LocalDate date, String userId);
}
