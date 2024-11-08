package com.psiboard.users_service.core.application.dto;

import com.psiboard.users_service.core.domain.UserRole;

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
    private String contact;
    private UserRole role;
}
