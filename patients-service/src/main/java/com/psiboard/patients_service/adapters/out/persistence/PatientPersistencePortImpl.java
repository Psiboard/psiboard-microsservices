package com.psiboard.patients_service.adapters.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.psiboard.patients_service.adapters.helpers.Utils;
import com.psiboard.patients_service.adapters.out.mapper.PatientMapper;
import com.psiboard.patients_service.core.application.dto.PatientRequestDto;
import com.psiboard.patients_service.core.application.dto.PatientResponseDto;
import com.psiboard.patients_service.core.application.dto.UpdatePatientRequestDto;
import com.psiboard.patients_service.core.application.exception.BusinessException;
import com.psiboard.patients_service.core.application.exception.CustomGenericException;
import com.psiboard.patients_service.core.application.ports.out.PatientPersistencePort;
import com.psiboard.patients_service.core.domain.Patient;

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
        Optional<Patient> existingPatient = patientRepository.findByEmail(patient.getEmail());

        if (existingPatient.isPresent())
            throw new BusinessException("Este paciente já está cadastrado no sistema!");
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

    @Override
    public List<PatientResponseDto> findByUserId(String id) {
        return patientRepository.findByUserId(id).stream()
                .map(patientMapper::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public PatientResponseDto update(String id, UpdatePatientRequestDto patient) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new CustomGenericException("Paciente com id " + id + " não foi encontrado"));
        patientMapper.updateUserFromDto(patient, existingPatient);
        return patientMapper.toDto(patientRepository.save(existingPatient));
    }

    @Override
    public void delete(String id) {
        patientRepository.deleteById(id);
    }

    @Override
    public List<PatientResponseDto> findPatientByName(String name) {
        return patientRepository.findByNameContaining(name).stream()
                .map(Utils::convertToPatientResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public PatientResponseDto findOne(String id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new CustomGenericException("Paciente com id " + id + " não foi encontrado"));
        return patientMapper.toDto(patient);
    }
}
