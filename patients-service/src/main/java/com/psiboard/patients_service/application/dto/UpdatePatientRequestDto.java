package com.psiboard.patients_service.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdatePatientRequestDto {
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
}
