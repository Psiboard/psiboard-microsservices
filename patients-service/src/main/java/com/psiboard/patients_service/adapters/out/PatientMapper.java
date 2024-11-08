package com.psiboard.patients_service.adapters.out;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.psiboard.patients_service.core.application.dto.PatientRequestDto;
import com.psiboard.patients_service.core.application.dto.PatientResponseDto;
import com.psiboard.patients_service.core.application.dto.UpdatePatientRequestDto;
import com.psiboard.patients_service.core.domain.Patient;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    @Mapping(target = "id", ignore = true) // O ID é gerado automaticamente
    Patient toEntity(PatientRequestDto patientRequestDto);

    PatientResponseDto toDto(Patient patient);
    
    void updateUserFromDto(UpdatePatientRequestDto dto, @MappingTarget Patient patient);
}
