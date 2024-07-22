package com.elifgenc.service.business.services;

import com.elifgenc.service.data.entity.RefreshToken;
import com.elifgenc.service.data.entity.User;

public interface RefreshTokenService {
    RefreshToken generateRefreshTokeByUser(User user);

    RefreshToken validateRefreshTokenByToken(String refreshToken);


}
