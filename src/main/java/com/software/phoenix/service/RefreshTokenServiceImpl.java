package com.software.phoenix.service;

import com.software.phoenix.model.User;
import com.software.phoenix.repository.RefreshTokenRepository;
import com.software.phoenix.service.interfaces.RefreshTokenService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public void deleteRefreshToken(String refreshToken) {
        refreshTokenRepository.deleteByRefreshToken(refreshToken);
    }

    public void deleteRefreshToken(Long id) {
        refreshTokenRepository.deleteById(id);
    }

    public User getUserByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow(
                () -> new NoSuchElementException("Invalid refresh token")).getUser();
    }
}
