package com.psiboard.users_service.core.application.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    public void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    public void testHandleBusinessException() {
        BusinessException businessException = new BusinessException("Business error");
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleBusinessException(businessException);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        assertEquals("Business error", response.getBody().getMessage());
    }

    @Test
    public void testHandleCustomGenericException() {
        CustomGenericException customGenericException = new CustomGenericException("Custom error");
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleCustomGenericException(customGenericException);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Custom error", response.getBody().getMessage());
    }

    @Test
    public void testHandleGeneralException() {
        Exception exception = new Exception("General error");
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleGeneralException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Ocorreu um erro: General error", response.getBody().getMessage());
    }
}
