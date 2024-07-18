package com.elifgenc.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDTO {
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
}
