package com.psiboard.users_service.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.psiboard.users_service.application.dto.UpdateUserRequestDto;
import com.psiboard.users_service.application.dto.UserRequestDto;
import com.psiboard.users_service.application.dto.UserResponseDto;
import com.psiboard.users_service.application.exception.CustomGenericException;
import com.psiboard.users_service.domain.User;
import com.psiboard.users_service.framework.adapters.out.UserMapper;
import com.psiboard.users_service.framework.adapters.out.UserPersistencePortImpl;
import com.psiboard.users_service.framework.adapters.out.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserPersistencePortImplTest {

    @InjectMocks
    private UserPersistencePortImpl userPersistencePortImpl;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Test
    @DisplayName("Deve criar um novo usuário")
    void shouldCreateUser() {
        UserRequestDto userRequestDto = new UserRequestDto();
        User userEntity = new User();
        User savedUserEntity = new User();
        UserResponseDto userResponseDto = new UserResponseDto();

        when(userMapper.toEntity(userRequestDto)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(savedUserEntity);
        when(userMapper.toDto(savedUserEntity)).thenReturn(userResponseDto);

        UserResponseDto result = userPersistencePortImpl.create(userRequestDto);

        assertNotNull(result);
        verify(userMapper).toEntity(userRequestDto);
        verify(userRepository).save(userEntity);
        verify(userMapper).toDto(savedUserEntity);
    }

    @Test
    @DisplayName("Deve retornar todos os usuáios")
    void shouldReturnAllUsers() {
        User user1 = new User();
        User user2 = new User();
        UserResponseDto userDto1 = new UserResponseDto();
        UserResponseDto userDto2 = new UserResponseDto();

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
        when(userMapper.toDto(user1)).thenReturn(userDto1);
        when(userMapper.toDto(user2)).thenReturn(userDto2);

        List<UserResponseDto> result = userPersistencePortImpl.findAll();

        assertEquals(2, result.size());
        verify(userRepository).findAll();
        verify(userMapper, times(2)).toDto(any(User.class));

    }

    @Test
    @DisplayName("Deve retornar um usuário encontrado")
    void testFindById_UserFound() {
        String userId = "1";
        User user = new User();
        UserResponseDto userResponseDto = new UserResponseDto();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(userResponseDto);

        UserResponseDto result = userPersistencePortImpl.findById(userId);

        assertNotNull(result);
        verify(userRepository).findById(userId);
        verify(userMapper).toDto(user);
    }

    @Test
    @DisplayName("Deve lançar um erro ao não encontrar um usuário")
    void testFindById_UserNotFound() {
        String userId = "1";

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(CustomGenericException.class, () -> {
            userPersistencePortImpl.findById(userId);
        });

        String expectedMessage = "Usuário com id " + userId + " não foi encontrado";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(userRepository).findById(userId);
    }

    @Test
    @DisplayName("Deve atualizar um usuário")
    void testUpdate() {
        String userId = "1";
        UpdateUserRequestDto updateUserRequestDto = new UpdateUserRequestDto();
        User existingUser = new User();
        User updatedUser = new User();
        UserResponseDto userResponseDto = new UserResponseDto();

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        doNothing().when(userMapper).updateUserFromDto(updateUserRequestDto, existingUser);
        when(userRepository.save(existingUser)).thenReturn(updatedUser);
        when(userMapper.toDto(updatedUser)).thenReturn(userResponseDto);

        UserResponseDto result = userPersistencePortImpl.update(userId, updateUserRequestDto);

        assertNotNull(result);
        verify(userRepository).findById(userId);
        verify(userMapper).updateUserFromDto(updateUserRequestDto, existingUser);
        verify(userRepository).save(existingUser);
        verify(userMapper).toDto(updatedUser);
    }

    @Test
    @DisplayName("Deve deletar um usuário")
    void testDelete() {
        String userId = "1";

        doNothing().when(userRepository).deleteById(userId);

        userPersistencePortImpl.delete(userId);

        verify(userRepository).deleteById(userId);
    }

    @Test
    @DisplayName("Deve retornar instancia de userRepository")
    void testGetUserRepository_Instance() {
        assertNotNull(userPersistencePortImpl.getUserRepository());
        assertEquals(userRepository, userPersistencePortImpl.getUserRepository());
    }

}
