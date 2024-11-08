package com.psiboard.users_service.adapters.in;

import org.springframework.web.bind.annotation.RestController;

import com.psiboard.users_service.core.application.dto.PatientResponseDto;
import com.psiboard.users_service.core.application.dto.UpdateUserRequestDto;
import com.psiboard.users_service.core.application.dto.UserResponseDto;
import com.psiboard.users_service.core.application.ports.in.UserServiceInputPort;
import com.psiboard.users_service.adapters.out.feign.PatientFeignClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("users")
public class UserRestController {

    private final UserServiceInputPort serviceInputPort;
    private final PatientFeignClient patientFeignClient;

    public UserRestController(UserServiceInputPort serviceInputPort, PatientFeignClient patientFeignClient) {
        this.serviceInputPort = serviceInputPort;
        this.patientFeignClient = patientFeignClient;
    }

    @GetMapping()
    public List<UserResponseDto> findAll() {
        return serviceInputPort.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(serviceInputPort.findById(id));
    }

    @GetMapping("/{userId}/patients")
    @CircuitBreaker(name = "users-service", fallbackMethod = "getPatientsFallback")
    public ResponseEntity<List<PatientResponseDto>> findPatientsForUser(@PathVariable String userId) {
        return patientFeignClient.findPatientsByUserId(userId);
    }

    @GetMapping("/user/{email}")
    public UserResponseDto findUserByEmail(@PathVariable String email) {
        return serviceInputPort.findByEmail(email);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable String id,
            @RequestBody UpdateUserRequestDto updateUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceInputPort.update(id, updateUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        serviceInputPort.delete(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Map<String, Object>> getPatientsFallback(String userId, Throwable throwable) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message", "O serviço de pacientes está temporariamente indisponível");
        responseBody.put("data", Collections.emptyList());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(responseBody);
    }

}
