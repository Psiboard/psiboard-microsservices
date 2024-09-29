package com.psiboard.users_service.framework.adapters.out.feign;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.Collections;
import com.psiboard.users_service.application.dto.PatientResponseDto;

@Component
public class PatientClientFallback implements PatientFeignClient {

    @Override
    public ResponseEntity<List<PatientResponseDto>> findPatientsByUserId(String userId) {
        System.out.println("Caiu no PatientClientFallback...");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                         .body(Collections.emptyList());
    }
}
