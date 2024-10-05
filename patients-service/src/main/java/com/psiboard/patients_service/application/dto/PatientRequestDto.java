package com.psiboard.patients_service.application.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequestDto {
    private String name;
    private Integer age;
    private String email;
    private String phone;
    private String street;
    private String district;
    private String city;
    private String state;
    private String zip_code;
    private String additional_info;
    private String user_id;
}
