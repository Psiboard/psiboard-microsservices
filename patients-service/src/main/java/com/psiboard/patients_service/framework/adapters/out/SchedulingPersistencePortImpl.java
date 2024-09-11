package com.psiboard.patients_service.framework.adapters.out;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.psiboard.patients_service.application.dto.SchedulingRequestDto;
import com.psiboard.patients_service.application.dto.SchedulingResponseDto;
import com.psiboard.patients_service.application.ports.out.SchedulingPersistencePort;
import com.psiboard.patients_service.domain.Scheduling;

@Repository
public class SchedulingPersistencePortImpl implements SchedulingPersistencePort {

    private final SchedulingRepository schedulingRepository;
    private final SchedulingMapper schedulingMapper;

    public SchedulingPersistencePortImpl(SchedulingRepository schedulingRepository, SchedulingMapper schedulingMapper) {
        this.schedulingRepository = schedulingRepository;
        this.schedulingMapper = schedulingMapper;
    }

    @Override
    public SchedulingResponseDto create(SchedulingRequestDto patient) {
        Scheduling schedulingDomain = schedulingMapper.toEntity(patient);
        Scheduling savedScheduling = schedulingRepository.save(schedulingDomain);
        return schedulingMapper.toDto(savedScheduling);
    }

    @Override
    public List<SchedulingResponseDto> findAll() {
        return schedulingRepository.findAll().stream()
                .map(schedulingMapper::toDto)
                .collect(Collectors.toList());
    }

}
