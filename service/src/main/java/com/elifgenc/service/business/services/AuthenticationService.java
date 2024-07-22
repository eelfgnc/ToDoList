package com.elifgenc.service.business.services;

import com.elifgenc.service.business.dto.request.AuthenticationRequestDTO;
import com.elifgenc.service.business.dto.request.RefreshTokenRequestDTO;
import com.elifgenc.service.business.dto.request.RegistrationRequestDTO;
import com.elifgenc.service.business.dto.response.AuthenticationResponseDTO;

public interface AuthenticationService {
    void register(RegistrationRequestDTO request);

    AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request);

    AuthenticationResponseDTO generateToken(RefreshTokenRequestDTO refreshTokenRequestDTO);
}
