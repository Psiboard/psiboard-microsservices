package com.psiboard.users_service.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponseDto {
    public PatientResponseDto(String string) {}

    private String id;
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