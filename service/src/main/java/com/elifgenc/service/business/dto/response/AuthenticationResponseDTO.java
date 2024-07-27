package com.elifgenc.service.business.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationResponseDTO {
    private String email;
    private String password;
    private String accessToken;
    private String refreshToken;
}