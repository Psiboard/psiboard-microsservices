package com.psiboard.patients_service.framework.adapters.out;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.psiboard.patients_service.application.dto.PatientRequestDto;
import com.psiboard.patients_service.application.dto.PatientResponseDto;
import com.psiboard.patients_service.application.ports.out.PatientPersistencePort;
import com.psiboard.patients_service.domain.Patient;

@Repository
public class PatientPersistencePortImpl implements PatientPersistencePort {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientPersistencePortImpl(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    @Override
    public PatientResponseDto create(PatientRequestDto patient) {
        Patient patientDomain = patientMapper.toEntity(patient);
        Patient savedPatient = patientRepository.save(patientDomain);
        return patientMapper.toDto(savedPatient);
    }

    @Override
    public List<PatientResponseDto> findAll() {
        return patientRepository.findAll().stream()
                .map(patientMapper::toDto)
                .collect(Collectors.toList());
    }
}
