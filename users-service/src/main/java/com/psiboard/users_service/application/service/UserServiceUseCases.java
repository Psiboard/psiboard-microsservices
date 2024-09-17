package com.psiboard.users_service.application.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.psiboard.users_service.application.dto.UpdateUserRequestDto;
import com.psiboard.users_service.application.dto.UserRequestDto;
import com.psiboard.users_service.application.dto.UserResponseDto;
import com.psiboard.users_service.application.ports.in.UserServiceInputPort;
import com.psiboard.users_service.application.ports.out.UserPersistencePort;
import com.psiboard.users_service.framework.adapters.out.UserRepository;

@Service
public class UserServiceUseCases implements UserServiceInputPort {

    private final UserPersistencePort userPersistencePort;

    public UserServiceUseCases(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public UserResponseDto create(UserRequestDto user) {
        return userPersistencePort.create(user);
    }

    @Override
    public List<UserResponseDto> findAll() {
        return userPersistencePort.findAll();
    }

    @Override
    public UserResponseDto findById(String id) {
        return userPersistencePort.findById(id);
    }

    @Override
    public UserRepository getUserRepository() {
        return userPersistencePort.getUserRepository();
    }

    @Override
    public UserResponseDto update(String id, UpdateUserRequestDto user) {
        return userPersistencePort.update(id, user);
    }

    @Override
    public void delete(String id) {
        userPersistencePort.delete(id);
    }

    @Override
    public UserResponseDto findByEmail(String email) {
        return userPersistencePort.findByEmail(email);
    }

}
