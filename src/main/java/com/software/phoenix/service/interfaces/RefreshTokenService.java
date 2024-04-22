package com.software.phoenix.service.interfaces;

import com.software.phoenix.model.User;
import com.software.phoenix.repository.RefreshTokenRepository;

import java.util.NoSuchElementException;

public interface RefreshTokenService {
    void deleteRefreshToken(String refreshToken);
    void deleteRefreshToken(Long id);
    User getUserByRefreshToken(String refreshToken);
}
