package com.software.phoenix.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpRequest {
    @NotEmpty(message = "The login is required.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Login contains invalid characters")
    private String login;

    @NotEmpty(message = "The password is required.")
    private String password;

    @NotEmpty(message = "The confirmation password is required.")
    private String passwordConfirmation;

    @NotEmpty(message = "The fullname is required.")
    @Size(min = 2, max = 50, message = "The length of full name must be between 2 and 50 characters.")
    private String fullname;
    private String avatarUrl;
}