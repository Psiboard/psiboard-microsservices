package com.psiboard.users_service.adapters.out.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.psiboard.users_service.core.application.dto.PatientResponseDto;

import java.util.List;

@FeignClient(name = "patients-service", url = "http://localhost:8082", fallback = PatientClientFallback.class)
public interface PatientFeignClient {

    @GetMapping("/patients/user/{userId}")
    public ResponseEntity<List<PatientResponseDto>> findPatientsByUserId(@PathVariable("userId") String userId);

}
