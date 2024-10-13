package com.psiboard.users_service.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.psiboard.users_service.application.dto.PatientResponseDto;
import com.psiboard.users_service.application.dto.UpdateUserRequestDto;
import com.psiboard.users_service.application.dto.UserResponseDto;
import com.psiboard.users_service.application.ports.in.UserServiceInputPort;
import com.psiboard.users_service.framework.adapters.in.UserRestController;
import com.psiboard.users_service.framework.adapters.out.feign.PatientFeignClient;

@ExtendWith(MockitoExtension.class)
public class UserRestControllerTest {

    @Mock
    private UserServiceInputPort serviceInputPort;

    @Mock
    private PatientFeignClient patientFeignClient;

    @InjectMocks
    private UserRestController userRestController;

    private UserResponseDto userResponseDto;
    private UpdateUserRequestDto updateUserRequestDto;
    private List<UserResponseDto> userResponseDtoList;
    private List<PatientResponseDto> patientResponseDtoList;

    @BeforeEach
    void setUp() {
        userResponseDto = new UserResponseDto();
        updateUserRequestDto = new UpdateUserRequestDto();
        userResponseDtoList = Collections.singletonList(userResponseDto);
        patientResponseDtoList = Collections.singletonList(new PatientResponseDto());
    }

    @Test
    void testFindAll() {
        when(serviceInputPort.findAll()).thenReturn(userResponseDtoList);

        List<UserResponseDto> result = userRestController.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(serviceInputPort).findAll();
    }

    @Test
    void testFindById() {
        String userId = "1";
        when(serviceInputPort.findById(userId)).thenReturn(userResponseDto);

        ResponseEntity<UserResponseDto> result = userRestController.findById(userId);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(userResponseDto, result.getBody());
        verify(serviceInputPort).findById(userId);
    }

    @Test
    void testFindPatientsForUser() {
        String userId = "1";
        when(patientFeignClient.findPatientsByUserId(userId)).thenReturn(ResponseEntity.ok(patientResponseDtoList));

        ResponseEntity<List<PatientResponseDto>> result = userRestController.findPatientsForUser(userId);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(patientResponseDtoList, result.getBody());
        verify(patientFeignClient).findPatientsByUserId(userId);
    }

    @Test
    void testFindUserByEmail() {
        String email = "test@example.com";
        when(serviceInputPort.findByEmail(email)).thenReturn(userResponseDto);

        UserResponseDto result = userRestController.findUserByEmail(email);

        assertNotNull(result);
        assertEquals(userResponseDto, result);
        verify(serviceInputPort).findByEmail(email);
    }

    @Test
    void testUpdate() {
        String userId = "1";
        when(serviceInputPort.update(userId, updateUserRequestDto)).thenReturn(userResponseDto);

        ResponseEntity<UserResponseDto> result = userRestController.update(userId, updateUserRequestDto);

        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(userResponseDto, result.getBody());
        verify(serviceInputPort).update(userId, updateUserRequestDto);
    }

    @Test
    void testDelete() {
        String userId = "1";

        ResponseEntity<Void> result = userRestController.delete(userId);

        assertNotNull(result);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(serviceInputPort).delete(userId);
    }

    @Test
    void testGetPatientsFallback() {
        String userId = "1";
        Throwable throwable = new RuntimeException("Service unavailable");

        ResponseEntity<Map<String, Object>> result = userRestController.getPatientsFallback(userId, throwable);

        assertNotNull(result);
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, result.getStatusCode());
        assertEquals("O serviço de pacientes está temporariamente indisponível", result.getBody().get("message"));
        assertEquals(Collections.emptyList(), result.getBody().get("data"));
    }
}
