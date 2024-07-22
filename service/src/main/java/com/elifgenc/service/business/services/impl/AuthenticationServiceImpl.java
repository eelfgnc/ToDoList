package com.elifgenc.service.business.services.impl;

import com.elifgenc.service.business.services.AuthenticationService;
import com.elifgenc.service.business.services.RefreshTokenService;
import com.elifgenc.service.config.JwtService;
import com.elifgenc.service.business.dto.request.AuthenticationRequestDTO;
import com.elifgenc.service.business.dto.request.RefreshTokenRequestDTO;
import com.elifgenc.service.business.dto.request.RegistrationRequestDTO;
import com.elifgenc.service.business.dto.response.AuthenticationResponseDTO;
import com.elifgenc.service.data.entity.Role;
import com.elifgenc.service.data.entity.RefreshToken;
import com.elifgenc.service.data.entity.User;
import com.elifgenc.service.data.entity.UserRole;
import com.elifgenc.service.data.repository.RoleRepository;
import com.elifgenc.service.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public void register(RegistrationRequestDTO request) {
        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("ROLE USER was not initiated"));

        var user = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .build();

        List<UserRole> userRoleList = new ArrayList<>();
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);
        userRoleList.add(userRole);

        user.setUserRoles(userRoleList);

        userRepository.save(user);
    }

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(request.getEmail()));
        user.setLastLoaginDate(LocalDateTime.now());

        User updateUser = userRepository.save(user);
        RefreshToken  refreshToken = refreshTokenService.generateRefreshTokeByUser(updateUser);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var jwtToken = jwtService.generateToken(user.getEmail());
        return AuthenticationResponseDTO.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken.getToken())
                .build();
    }

    public AuthenticationResponseDTO generateToken(RefreshTokenRequestDTO refreshTokenRequestDTO){
        RefreshToken refreshToken = refreshTokenService.validateRefreshTokenByToken(refreshTokenRequestDTO.getRefreshToken());
        User user = refreshToken.getUser();
        return AuthenticationResponseDTO
                .builder()
                .accessToken(jwtService.generateToken(user.getEmail()))
                .refreshToken(refreshTokenService.generateRefreshTokeByUser(user).getToken())
                .build();
    }
}