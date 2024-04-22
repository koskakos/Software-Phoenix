package com.software.phoenix.controller;

import com.software.phoenix.model.request.SignInRequest;
import com.software.phoenix.model.request.SignUpRequest;
import com.software.phoenix.model.response.JwtAuthenticationResponse;
import com.software.phoenix.service.CookieService;
import com.software.phoenix.service.interfaces.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService<JwtAuthenticationResponse> authenticationService;
    private final CookieService cookieService;

    public AuthenticationController(AuthenticationService<JwtAuthenticationResponse> authenticationService, CookieService cookieService) {
        this.authenticationService = authenticationService;
        this.cookieService = cookieService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(HttpServletResponse response,
                                    @Valid @RequestBody SignUpRequest request) {
        JwtAuthenticationResponse authenticationResponse = authenticationService.signup(request);
        cookieService.setAuthenticationCookies(response, authenticationResponse);
        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(HttpServletResponse response,
                                    @Valid @RequestBody SignInRequest request) {
        JwtAuthenticationResponse authenticationResponse = authenticationService.signin(request);
        cookieService.setAuthenticationCookies(response, authenticationResponse);
        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(HttpServletResponse response,
                                                             @CookieValue("refreshToken") String refreshToken) {
        JwtAuthenticationResponse authenticationResponse = authenticationService.refresh(refreshToken);
        cookieService.setAuthenticationCookies(response, authenticationResponse);
        return ResponseEntity.ok(authenticationResponse);
    }
}