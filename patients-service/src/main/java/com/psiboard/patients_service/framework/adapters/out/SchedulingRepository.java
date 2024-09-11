package com.psiboard.patients_service.framework.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;

import com.psiboard.patients_service.domain.Scheduling;

public interface SchedulingRepository extends JpaRepository<Scheduling, String> {
}
