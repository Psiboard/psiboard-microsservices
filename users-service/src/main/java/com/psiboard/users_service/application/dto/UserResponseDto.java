package com.psiboard.users_service.application.dto;

import com.psiboard.users_service.domain.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private String id;
    private String name;
    private String email;
    private String password;
    private String contact;
    private UserRole role;
}
