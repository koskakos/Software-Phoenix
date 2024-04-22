package com.software.phoenix.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SignInRequest {
    @NotEmpty(message = "The login is required.")
    private String login;

    @NotEmpty(message = "The password is required.")
    private String password;
}