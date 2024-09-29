package com.psiboard.patients_service.framework.adapters.out;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.psiboard.patients_service.domain.Scheduling;

public interface SchedulingRepository extends JpaRepository<Scheduling, String> {

    // Verifica se o paciente já tem um agendamento na mesma data
    boolean existsByPatientIdAndDate(String patientId, LocalDate date);

    // Verifica se existe um agendamento em uma data e hora específicas
    boolean existsByDateAndHour(LocalDate date, String hour);
}
