package com.psiboard.patients_service.framework.adapters.in;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psiboard.patients_service.application.dto.PatientRequestDto;
import com.psiboard.patients_service.application.dto.PatientResponseDto;
import com.psiboard.patients_service.application.dto.UpdatePatientRequestDto;
import com.psiboard.patients_service.application.ports.in.PatientServiceInputPort;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("patients")
public class PatientRestController {

    private final PatientServiceInputPort patientServiceInputPort;

    public PatientRestController(PatientServiceInputPort patientServiceInputPort) {
        this.patientServiceInputPort = patientServiceInputPort;
    }

    @GetMapping()
    public List<PatientResponseDto> findAll() {
        return patientServiceInputPort.findAll();
    }

    @GetMapping("/{id}")
    public List<PatientResponseDto> findByUserId(@PathVariable String id) {
        return patientServiceInputPort.findByUserId(id);
    }

    @GetMapping("/patient")
    public List<PatientResponseDto> findPatientByName(@RequestParam String name) {
        return patientServiceInputPort.findPatientByName(name);
    }

    @PostMapping()
    public ResponseEntity<PatientResponseDto> create(@RequestBody PatientRequestDto patient) {
        PatientResponseDto newPatient = patientServiceInputPort.create(patient);
        return ResponseEntity.ok(newPatient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDto> update(@PathVariable String id,
            @RequestBody UpdatePatientRequestDto updateUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientServiceInputPort.update(id, updateUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        patientServiceInputPort.delete(id);
        return ResponseEntity.noContent().build();
    }

}
