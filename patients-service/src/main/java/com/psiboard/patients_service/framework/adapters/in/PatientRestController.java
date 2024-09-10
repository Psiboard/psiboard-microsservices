package com.psiboard.patients_service.framework.adapters.in;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psiboard.patients_service.application.dto.PatientRequestDto;
import com.psiboard.patients_service.application.dto.PatientResponseDto;
import com.psiboard.patients_service.application.ports.in.PatientServiceInputPort;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

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

    @PostMapping()
    public ResponseEntity<PatientResponseDto> create(@RequestBody PatientRequestDto patient) {
        PatientResponseDto newPatient = patientServiceInputPort.create(patient);
        return ResponseEntity.ok(newPatient);
    }

}
