package com.elifgenc.service.advice;

import com.elifgenc.service.business.dto.response.ErrorResponseDTO;
import com.elifgenc.service.constant.ErrorMessage;
import com.elifgenc.service.exception.ObjectNotFoundException;
import com.elifgenc.service.exception.RefreshTokenException;
import com.elifgenc.service.exception.ToDoServiceException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ToDoControllerAdvice {

    @ExceptionHandler(value = ExpiredJwtException.class)
    public ResponseEntity<ErrorResponseDTO> expiredJwtToken(ExpiredJwtException e) {
        log.error(e.getMessage());
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.createErrorResponse(ErrorMessage.EXPIRED_USER_TOKEN);
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> exceptionHandşler(Exception e) {
        log.error("Beklenmeyen bir hata ile karşılaşıldı.", e);
        return new ResponseEntity<>("Beklenmeyen bir hata ile karşılaşıldı.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

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
