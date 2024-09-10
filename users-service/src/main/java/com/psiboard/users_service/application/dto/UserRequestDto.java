package com.psiboard.users_service.application.dto;

import com.psiboard.users_service.domain.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    @NotBlank(message = "Nome não pode ser vazio!")
    @NotNull(message = "Nome não pode ser nulo!")
    private String name;

    @NotBlank(message = "Email não pode ser vazio!")
    @Email(message = "Digite um email válido")
    private String email;

    @Size(min = 6, message = "A senha precisa ter no minimo 6 caracteres!")
    private String password;

    @NotBlank(message = "Contato não pode ser vazio!")
    @NotNull(message = "Contato não pode ser nulo!")
    private String contact;
    
    // @NotNull(message = "Role não pode ser nulo!")
    // @NotBlank(message = "Role não pode ser vazio!")
    private UserRole role;
}
