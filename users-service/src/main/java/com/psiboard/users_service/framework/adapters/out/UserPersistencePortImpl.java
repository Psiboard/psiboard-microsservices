package com.psiboard.users_service.framework.adapters.out;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import com.psiboard.users_service.framework.helpers.Utils;

import ch.qos.logback.classic.pattern.Util;

import com.psiboard.users_service.application.dto.UpdateUserRequestDto;
import com.psiboard.users_service.application.dto.UserRequestDto;
import com.psiboard.users_service.application.dto.UserResponseDto;
import com.psiboard.users_service.application.exception.CustomGenericException;
import com.psiboard.users_service.application.ports.out.UserPersistencePort;
import com.psiboard.users_service.domain.User;

@Repository
public class UserPersistencePortImpl implements UserPersistencePort {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserPersistencePortImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponseDto create(UserRequestDto user) {
        // Mapeando UserRequestDto para a entidade User
        User userDomain = userMapper.toEntity(user);
        User savedUser = userRepository.save(userDomain);
        // Retornando um UserResponseDto
        return userMapper.toDto(savedUser);
    }

    @Override
    public UserRepository getUserRepository() {
        return this.userRepository;
    }

    @Override
    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto findById(String id) {
        return userMapper.toDto(userRepository.findById(id)
                .orElseThrow(() -> new CustomGenericException("Usuário com id " + id + " não foi encontrado")));
    }

    @Override
    public UserResponseDto update(String id, UpdateUserRequestDto user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new CustomGenericException("Usuário com id " + id + " não foi encontrado"));
        userMapper.updateUserFromDto(user, existingUser);
        return userMapper.toDto(userRepository.save(existingUser));
    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDto findByEmail(String email) {
        return Utils.convertToUserResponseDto(userRepository.findByEmail(email));
    }

}
