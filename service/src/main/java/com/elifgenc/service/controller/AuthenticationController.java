package com.elifgenc.service.controller;

import com.elifgenc.service.business.dto.request.AuthenticationRequestDTO;
import com.elifgenc.service.business.dto.request.RefreshTokenRequestDTO;
import com.elifgenc.service.business.dto.request.RegistrationRequestDTO;
import com.elifgenc.service.business.dto.response.AuthenticationResponseDTO;
import org.springframework.http.ResponseEntity;

public interface AuthenticationController {

    ResponseEntity<?> register(RegistrationRequestDTO registrationRequest);

    ResponseEntity<AuthenticationResponseDTO> authenticate(AuthenticationRequestDTO authenticationRequest);

    ResponseEntity<AuthenticationResponseDTO> refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);
}
