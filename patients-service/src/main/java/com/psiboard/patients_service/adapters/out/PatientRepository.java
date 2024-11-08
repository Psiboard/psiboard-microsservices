package com.psiboard.patients_service.adapters.out;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.psiboard.patients_service.core.domain.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, String> {

    @Query(value = "SELECT p FROM Patient p WHERE p.user_id = :userId")
    List<Patient> findByUserId(String userId);

    Optional<Patient> findByEmail(String email);

    // @Query("SELECT p FROM Patient p WHERE p.name LIKE %:name%")
    // List<Patient> findPatientByName(String name);
    List<Patient> findByNameContaining(String name);


}
