package com.software.phoenix.service.interfaces;

import com.software.phoenix.model.request.SignInRequest;
import com.software.phoenix.model.request.SignUpRequest;

public interface AuthenticationService<T> {
    T signup(SignUpRequest request);
    T signin(SignInRequest request);
    T refresh(String refreshToken);
}
