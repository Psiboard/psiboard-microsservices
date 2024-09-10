package com.psiboard.patients_service.framework.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;

import com.psiboard.patients_service.domain.Patient;

public interface PatientRepository extends JpaRepository<Patient, String> {

}
