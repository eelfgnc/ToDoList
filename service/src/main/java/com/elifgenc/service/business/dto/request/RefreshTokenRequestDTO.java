package com.elifgenc.service.business.dto.request;

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
public class RefreshTokenRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Refresh token boş olamaz.")
    private String refreshToken;
}
