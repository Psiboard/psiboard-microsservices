package com.psiboard.users_service.framework.adapters.out.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import java.util.Collections;
import com.psiboard.users_service.application.dto.PatientResponseDto;

@Component
public class PatientClientFallback implements FallbackFactory<PatientFeignClient> {

    @Override
    public PatientFeignClient create(Throwable cause) {
        return new PatientFeignClient() {

            @Override
            public List<PatientResponseDto> getPatientsByUserId(String userId) {
                // Captura a causa do erro
                System.out.println("Fallback ativado devido a: " + cause.getMessage());

                // Retorna lista vazia como fallback
                return Collections.emptyList();
            }
        };
    }

}
