package com.elifgenc.service.business.dto.response;

import com.elifgenc.service.constant.ErrorMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Schema(name = "Error Response DTO", description = "Contains error information.")
public class ErrorResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(name = "message", description = "It is the message of the error.", example = "Kullanıcı bulunamadı.")
    private String message;
    @Schema(name = "internalMessage", description = "It is the internal message of the error.", example = "Kullanıcıya ait kayıt bulunamadı.")
    private String internalMessage;
    @Schema(name = "errorCode", description = "It is the code of the error.", example = "TODO-1524")
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
