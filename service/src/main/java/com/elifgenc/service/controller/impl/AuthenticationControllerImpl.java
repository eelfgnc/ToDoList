package com.elifgenc.service.controller.impl;

import com.elifgenc.service.controller.AuthenticationController;
import com.elifgenc.service.business.dto.request.AuthenticationRequestDTO;
import com.elifgenc.service.business.dto.request.RefreshTokenRequestDTO;
import com.elifgenc.service.business.dto.request.RegistrationRequestDTO;
import com.elifgenc.service.business.dto.response.AuthenticationResponseDTO;
import com.elifgenc.service.business.services.impl.AuthenticationServiceImpl;
import com.elifgenc.service.utils.frontend.ReactFrontend;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@CrossOrigin(origins = ReactFrontend.REACT_FRONTEND_PORT_URL)
@Tag(name = "Authentication", description = "This tag includes register, login, refresh token services.")
public class AuthenticationControllerImpl implements AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;

    @Override
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(
            @RequestBody @Valid RegistrationRequestDTO registrationRequest
    ) {
        authenticationService.register(registrationRequest);
        return ResponseEntity.accepted().build();
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(
            @RequestBody @Valid AuthenticationRequestDTO authenticationRequest
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @Override
    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponseDTO> refreshToken(@RequestBody @Valid RefreshTokenRequestDTO refreshTokenRequestDTO) {
        return ResponseEntity.ok(authenticationService.generateToken(refreshTokenRequestDTO));
    }
}