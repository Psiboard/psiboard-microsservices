package com.psiboard.patients_service.framework.adapters.out;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import com.psiboard.patients_service.framework.helpers.Utils;

import com.psiboard.patients_service.application.dto.SchedulingRequestDto;
import com.psiboard.patients_service.application.dto.SchedulingResponseDto;
import com.psiboard.patients_service.application.exception.BusinessException;
import com.psiboard.patients_service.application.exception.CustomGenericException;
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
    public SchedulingResponseDto create(SchedulingRequestDto scheduling) {
        // Validações de horario
        Utils.validateHourFormat(scheduling.getHour());
        Utils.validateHourRange(scheduling.getHour());

        // Regras de negócio de agendamentos
        checkPatientHasNoSchedulingOnSameDate(scheduling.getPatient_id(), scheduling.getDate());
        checkNoSchedulingOnSameDateTime(scheduling.getDate(), scheduling.getHour());

        Scheduling schedulingDomain = schedulingMapper.toEntity(scheduling);
        Scheduling savedScheduling = schedulingRepository.save(schedulingDomain);
        return schedulingMapper.toDto(savedScheduling);
    }

    @Override
    public List<SchedulingResponseDto> findAll() {
        return schedulingRepository.findAll().stream()
                .map(schedulingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SchedulingResponseDto update(String id, SchedulingRequestDto scheduling) {

        // Validações
        Utils.validateHourFormat(scheduling.getHour());
        Utils.validateHourRange(scheduling.getHour());
        Utils.validateTypeUpdateScheduling(scheduling.getType());
        checkNoSchedulingOnSameDateTime(scheduling.getDate(), scheduling.getHour());

        Scheduling existingScheduling = schedulingRepository.findById(id)
                .orElseThrow(() -> new CustomGenericException("Agendamento com id " + id + " não foi encontrado"));
        schedulingMapper.updateSchedulingFromDto(scheduling, existingScheduling);
        return schedulingMapper.toDto(schedulingRepository.save(existingScheduling));
    }

    @Override
    public void delete(String id) {
        schedulingRepository.deleteById(id);

    }

    private void checkPatientHasNoSchedulingOnSameDate(String patientId, LocalDate date) {
        boolean exists = schedulingRepository.existsByPatientIdAndDate(patientId, date);
        if (exists) {
            throw new BusinessException("O paciente já possui um agendamento nesta data.");
        }
    }

    private void checkNoSchedulingOnSameDateTime(LocalDate date, String hour) {
        boolean exists = schedulingRepository.existsByDateAndHour(date, hour);
        if (exists) {
            throw new BusinessException("Já existe um agendamento para essa data e hora.");
        }
    }

    @Override
    public List<SchedulingResponseDto> findSchedules(String id, LocalDate date) {
        return schedulingRepository.findByDateAndUserId(date, id).stream()
                .map(Utils::convertToSchedulingResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAvailableHours(LocalDate date, String userId) {
        List<Scheduling> schedulings = schedulingRepository.findByDateAndUserId(date, userId);

        List<String> allHours = Utils.generateAllHours();

        return allHours.stream()
                .filter(hour -> !schedulings.stream()
                        .anyMatch(scheduling -> scheduling.getHour().equals(hour)))
                .collect(Collectors.toList());

    }

}
