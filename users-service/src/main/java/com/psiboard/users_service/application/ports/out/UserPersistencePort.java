package com.psiboard.users_service.application.ports.out;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.psiboard.users_service.application.dto.UpdateUserRequestDto;
import com.psiboard.users_service.application.dto.UserRequestDto;
import com.psiboard.users_service.application.dto.UserResponseDto;
import com.psiboard.users_service.framework.adapters.out.UserRepository;

public interface UserPersistencePort {
    UserRepository getUserRepository();

    UserResponseDto create(UserRequestDto user);

    List<UserResponseDto> findAll();

    UserResponseDto findById(String id);

    UserResponseDto findByEmail(String email);

    UserResponseDto update(String id, UpdateUserRequestDto user);

    void delete(String id);
}
