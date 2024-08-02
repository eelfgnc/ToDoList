package com.elifgenc.service.advice;

import com.elifgenc.service.business.dto.response.ErrorResponseDTO;
import com.elifgenc.service.exception.ObjectNotFoundException;
import com.elifgenc.service.exception.RefreshTokenException;
import com.elifgenc.service.exception.ToDoServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ToDoControllerAdvice {

    @ExceptionHandler(value = ToDoServiceException.class)
    public ResponseEntity<ErrorResponseDTO> todoServiceException(ToDoServiceException e) {
        log.error(e.getInternalMessage());
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.createErrorResponse(e.getMessage(),e.getInternalMessage(),e.getCode());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ObjectNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> objectNotFound(ObjectNotFoundException e) {
        log.error(e.getInternalMessage());
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.createErrorResponse(e.getMessage(),e.getInternalMessage(),e.getCode());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = RefreshTokenException.class)
    public ResponseEntity<ErrorResponseDTO> refreshTokenException(RefreshTokenException e) {
        log.error(e.getInternalMessage());
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.createErrorResponse(e.getMessage(),e.getInternalMessage(),e.getCode());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.UNAUTHORIZED);
    }

}
