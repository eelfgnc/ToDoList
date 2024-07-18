package com.elifgenc.service.advice;

import com.elifgenc.service.dto.ErrorResponseDTO;
import com.elifgenc.service.exception.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ToDOControllerAdvice {

    @ExceptionHandler(value = ObjectNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> objectNotFound(ObjectNotFoundException e) {
        log.error(e.getInternalMessage());
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.createErrorResponse(e.getMessage(),e.getInternalMessage(),e.getCode());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }

}
