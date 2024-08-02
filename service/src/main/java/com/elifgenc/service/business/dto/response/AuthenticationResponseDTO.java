package com.elifgenc.service.business.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Schema(name = "Authentication Response DTO", description = "Contains authentication information.")
public class AuthenticationResponseDTO {
    @Schema(name = "email", description = "It is the email of the user.", example = "test@gmail.com")
    private String email;
    @Schema(name = "password", description = "It is the password of the user.", example = "*****")
    private String password;
    @Schema(name = "accessToken", description = "It is the access token of the user.", example = "*****")
    private String accessToken;
    @Schema(name = "refreshToken", description = "It is the refresh token of the user.", example = "*****")
    private String refreshToken;
}