package com.psiboard.patients_service.framework.adapters.out;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.psiboard.patients_service.application.dto.PatientRequestDto;
import com.psiboard.patients_service.application.dto.PatientResponseDto;
import com.psiboard.patients_service.domain.Patient;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    // Mapeamento de UserRequestDto para User
    @Mapping(target = "id", ignore = true) // O ID é gerado automaticamente
    Patient toEntity(PatientRequestDto userRequestDto);

    // Mapeamento de User para UserResponseDto
    PatientResponseDto toDto(Patient user);
}
