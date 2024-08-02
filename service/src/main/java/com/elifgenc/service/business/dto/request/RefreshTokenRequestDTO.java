package com.elifgenc.service.business.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Refresh Token Request DTO", description = "Contains refresh token.")
public class RefreshTokenRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Refresh token is not empty.")
    @Schema(name = "email", description = "It is the refresh token of the user.", example = "*****")
    private String refreshToken;
}
