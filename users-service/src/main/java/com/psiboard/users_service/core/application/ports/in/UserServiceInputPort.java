package com.psiboard.users_service.core.application.ports.in;

import java.util.List;
import com.psiboard.users_service.core.application.dto.UpdateUserRequestDto;
import com.psiboard.users_service.core.application.dto.UserRequestDto;
import com.psiboard.users_service.core.application.dto.UserResponseDto;
import com.psiboard.users_service.adapters.out.UserRepository;

public interface UserServiceInputPort {

    UserRepository getUserRepository();

    UserResponseDto create(UserRequestDto user);

    List<UserResponseDto> findAll();

    UserResponseDto findById(String id);

    UserResponseDto findByEmail(String email);

    UserResponseDto update(String id, UpdateUserRequestDto user);

    void delete(String id);
}
