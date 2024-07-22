package com.elifgenc.service.business.dto;

import com.elifgenc.service.constant.ErrorMessage;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ErrorResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String message;
    private String internalMessage;
    private String errorCode;

    public static ErrorResponseDTO createErrorResponse(String message, String internalMessage, String errorCode) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage(message);
        errorResponseDTO.setInternalMessage(internalMessage);
        errorResponseDTO.setErrorCode(errorCode);
        return errorResponseDTO;
    }

    public static ErrorResponseDTO createErrorResponse(ErrorMessage errorMessage) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage(errorResponseDTO.getMessage());
        errorResponseDTO.setInternalMessage(errorResponseDTO.getInternalMessage());
        errorResponseDTO.setErrorCode(errorResponseDTO.getErrorCode());
        return errorResponseDTO;
    }
}
