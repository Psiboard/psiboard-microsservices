package com.psiboard.users_service.framework.adapters.out.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;


@FeignClient(name = "patients-service")
public interface PatientFeignClient {

    @GetMapping("/patients/{userId}")
    <T> List<T> getPatientsByUserId(@PathVariable("userId") String userId);
    
}
