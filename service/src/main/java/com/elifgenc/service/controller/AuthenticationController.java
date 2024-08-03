package com.elifgenc.service.controller;

import com.elifgenc.service.business.dto.request.AuthenticationRequestDTO;
import com.elifgenc.service.business.dto.request.RefreshTokenRequestDTO;
import com.elifgenc.service.business.dto.request.RegistrationRequestDTO;
import com.elifgenc.service.business.dto.response.AuthenticationResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface AuthenticationController {
    @Operation(summary = "User register service",
            description = "It is used to create a user registration to use the application."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registration created successfully.")
    })
    ResponseEntity<?> register(RegistrationRequestDTO registrationRequest);

    @Operation(summary = "User login service",
            description = "Used to log in to use the app"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User login completed successfully.")
    })
    ResponseEntity<AuthenticationResponseDTO> authenticate(AuthenticationRequestDTO authenticationRequest);

    @Operation(summary = "Refresh token service",
            description = "Used to renew the renewal key."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Renew key successfully renewed")
    })
    ResponseEntity<AuthenticationResponseDTO> refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);
}
