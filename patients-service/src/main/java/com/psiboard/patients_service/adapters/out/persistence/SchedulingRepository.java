package com.psiboard.patients_service.adapters.out.persistence;

import java.time.LocalDate;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.psiboard.patients_service.core.domain.Scheduling;

public interface SchedulingRepository extends JpaRepository<Scheduling, String> {

    // Verifica se o paciente já tem um agendamento na mesma data
    boolean existsByPatientIdAndDate(String patientId, LocalDate date);

    // Verifica se existe um agendamento em uma data e hora específicas
    boolean existsByDateAndHour(LocalDate date, String hour);

    @Query("SELECT s FROM Scheduling s WHERE s.date = :date AND s.user_id = :userId")
    List<Scheduling> findByDateAndUserId(LocalDate date, String userId);
}
