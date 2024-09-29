package com.psiboard.patients_service.framework.adapters.out;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import com.psiboard.patients_service.application.dto.SchedulingRequestDto;
import com.psiboard.patients_service.application.dto.SchedulingResponseDto;
import com.psiboard.patients_service.domain.Patient;
import com.psiboard.patients_service.domain.Scheduling;

@Mapper(componentModel = "spring")
public interface SchedulingMapper {
    SchedulingMapper INSTANCE = Mappers.getMapper(SchedulingMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "patient", source = "patient_id", qualifiedByName = "uuidToPatient")
    Scheduling toEntity(SchedulingRequestDto schedulingRequestDto);

    @Mapping(source = "patient.id", target = "patient_id")
    SchedulingResponseDto toDto(Scheduling scheduling);

    @Mapping(target = "id", ignore = true) // Ignora o ID, pois não deve ser alterado em uma atualização
    @Mapping(target = "patient", source = "patient_id", qualifiedByName = "uuidToPatient") // Converte o patient_id para o objeto Patient
    void updateSchedulingFromDto(SchedulingRequestDto dto, @MappingTarget Scheduling scheduling);

    @Named("uuidToPatient")
    default Patient uuidToPatient(String patientId) {
        if (patientId == null) {
            return null;
        }
        Patient patient = new Patient();
        patient.setId(patientId);
        return patient;
    }

}
