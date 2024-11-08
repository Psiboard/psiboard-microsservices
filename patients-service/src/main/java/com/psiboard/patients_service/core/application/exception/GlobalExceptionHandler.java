package com.psiboard.patients_service.core.application.exception;

import java.time.format.DateTimeParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handler para BusinessException
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // Handler para CustomGenericException
    @ExceptionHandler(CustomGenericException.class)
    public ResponseEntity<ErrorResponse> handleCustomGenericException(CustomGenericException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Handler para outras exceções
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("Ocorreu um erro: " + ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handler para formato de Data invalido
    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<Object> handleDateTimeParseException(DateTimeParseException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Data inválida. O formato esperado é 'yyyy-MM-dd'");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
