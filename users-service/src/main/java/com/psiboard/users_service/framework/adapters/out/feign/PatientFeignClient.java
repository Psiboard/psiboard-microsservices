package com.psiboard.users_service.framework.adapters.out.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.psiboard.users_service.application.dto.PatientResponseDto;

import java.util.List;

@FeignClient(name = "patients-service", fallback = PatientClientFallback.class)
public interface PatientFeignClient {

    @GetMapping("/patients/{userId}")
    List<PatientResponseDto> getPatientsByUserId(@PathVariable("userId") String userId);

}
