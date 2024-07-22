package com.elifgenc.service.business.services.impl;

import com.elifgenc.service.business.services.RefreshTokenService;
import com.elifgenc.service.constant.ErrorMessage;
import com.elifgenc.service.data.entity.RefreshToken;
import com.elifgenc.service.data.entity.User;
import com.elifgenc.service.exception.RefreshTokenException;
import com.elifgenc.service.data.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    @Value(value = "${application.security.jwt.refresh-token-expired}")
    private Long refreshTokenExpired;

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken generateRefreshTokeByUser(User user) {
        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiredDateTime(LocalDateTime.now().plusHours(refreshTokenExpired))
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken validateRefreshTokenByToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token).orElseThrow(() -> new RefreshTokenException(ErrorMessage.REFRESH_TOKEN_NOT_FOUND));
        if(LocalDateTime.now().isAfter(refreshToken.getExpiredDateTime())) {
            throw new RefreshTokenException(ErrorMessage.REFRESH_TOKEN_NOT_VALID);
        }
        return refreshToken;
    }
}
