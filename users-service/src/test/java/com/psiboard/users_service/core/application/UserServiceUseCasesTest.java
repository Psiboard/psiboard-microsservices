package com.psiboard.users_service.core.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.psiboard.users_service.adapters.out.UserRepository;
import com.psiboard.users_service.core.application.dto.UpdateUserRequestDto;
import com.psiboard.users_service.core.application.dto.UserRequestDto;
import com.psiboard.users_service.core.application.dto.UserResponseDto;
import com.psiboard.users_service.core.application.ports.out.UserPersistencePort;
import com.psiboard.users_service.core.application.service.UserServiceUseCases;

public class UserServiceUseCasesTest {

    @Mock
    private UserPersistencePort userPersistencePort;

    @InjectMocks
    private UserServiceUseCases userServiceUseCases;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate() {
        UserRequestDto userRequest = new UserRequestDto();
        UserResponseDto userResponse = new UserResponseDto();
        when(userPersistencePort.create(userRequest)).thenReturn(userResponse);

        UserResponseDto result = userServiceUseCases.create(userRequest);

        assertEquals(userResponse, result);
        verify(userPersistencePort).create(userRequest);
    }

    @Test
    public void testFindAll() {
        List<UserResponseDto> userList = Arrays.asList(new UserResponseDto(), new UserResponseDto());
        when(userPersistencePort.findAll()).thenReturn(userList);

        List<UserResponseDto> result = userServiceUseCases.findAll();

        assertEquals(userList, result);
        verify(userPersistencePort).findAll();
    }

    @Test
    public void testFindById() {
        String id = "1";
        UserResponseDto userResponse = new UserResponseDto();
        when(userPersistencePort.findById(id)).thenReturn(userResponse);

        UserResponseDto result = userServiceUseCases.findById(id);

        assertEquals(userResponse, result);
        verify(userPersistencePort).findById(id);
    }

    @Test
    public void testGetUserRepository() {
        UserRepository userRepository = mock(UserRepository.class);
        when(userPersistencePort.getUserRepository()).thenReturn(userRepository);

        UserRepository result = userServiceUseCases.getUserRepository();

        assertEquals(userRepository, result);
        verify(userPersistencePort).getUserRepository();
    }

    @Test
    public void testUpdate() {
        String id = "1";
        UpdateUserRequestDto updateUserRequest = new UpdateUserRequestDto();
        UserResponseDto userResponse = new UserResponseDto();
        when(userPersistencePort.update(id, updateUserRequest)).thenReturn(userResponse);

        UserResponseDto result = userServiceUseCases.update(id, updateUserRequest);

        assertEquals(userResponse, result);
        verify(userPersistencePort).update(id, updateUserRequest);
    }

    @Test
    public void testDelete() {
        String id = "1";

        userServiceUseCases.delete(id);

        verify(userPersistencePort).delete(id);
    }

    @Test
    public void testFindByEmail() {
        String email = "test@example.com";
        UserResponseDto userResponse = new UserResponseDto();
        when(userPersistencePort.findByEmail(email)).thenReturn(userResponse);

        UserResponseDto result = userServiceUseCases.findByEmail(email);

        assertEquals(userResponse, result);
        verify(userPersistencePort).findByEmail(email);
    }
}