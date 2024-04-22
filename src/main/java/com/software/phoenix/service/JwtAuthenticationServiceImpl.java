package com.software.phoenix.service;

import com.software.phoenix.model.User;
import com.software.phoenix.model.request.SignInRequest;
import com.software.phoenix.model.request.SignUpRequest;
import com.software.phoenix.model.response.JwtAuthenticationResponse;
import com.software.phoenix.service.interfaces.AuthenticationService;
import com.software.phoenix.service.interfaces.RefreshTokenService;
import com.software.phoenix.service.interfaces.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JwtAuthenticationServiceImpl implements AuthenticationService<JwtAuthenticationResponse> {
    private final UserService userService;
    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;

    public JwtAuthenticationServiceImpl(UserService userService, JwtServiceImpl jwtService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, RefreshTokenService refreshTokenService) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        User user = userService.createUser(request, passwordEncoder);
        return JwtAuthenticationResponse.builder()
                .accessToken(jwtService.generateAccessToken(user))
                .refreshToken(jwtService.generateRefreshToken(user).getRefreshToken())
                .build();
    }

    @Override
    public JwtAuthenticationResponse signin(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        User user = userService.getUserByLogin(request.getLogin());
        refreshTokenService.deleteRefreshToken(user.getId());
        return JwtAuthenticationResponse.builder()
                .accessToken(jwtService.generateAccessToken(user))
                .refreshToken(jwtService.generateRefreshToken(user).getRefreshToken())
                .build();
    }

    @Override
    public JwtAuthenticationResponse refresh(String refreshToken) {
        User user = refreshTokenService.getUserByRefreshToken(refreshToken);
        refreshTokenService.deleteRefreshToken(refreshToken);
        return JwtAuthenticationResponse.builder()
                .accessToken(jwtService.generateAccessToken(user))
                .refreshToken(jwtService.generateRefreshToken(user).getRefreshToken())
                .build();
    }
}
