package com.psiboard.users_service.framework.adapters.in;

import org.springframework.web.bind.annotation.RestController;

import com.psiboard.users_service.application.dto.UpdateUserRequestDto;
import com.psiboard.users_service.application.dto.UserResponseDto;
import com.psiboard.users_service.application.exception.CustomGenericException;
import com.psiboard.users_service.application.ports.in.UserServiceInputPort;
import com.psiboard.users_service.framework.adapters.out.feign.PatientFeignClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.Collections;
import java.util.List;
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
    public <T> List<T> getPatientsForUser(@PathVariable String userId) {
        return patientFeignClient.getPatientsByUserId(userId);
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

    private CustomGenericException getPatientsFallback() {
        return new CustomGenericException("O Serviço está temporariamente indisponível");
    }

}
