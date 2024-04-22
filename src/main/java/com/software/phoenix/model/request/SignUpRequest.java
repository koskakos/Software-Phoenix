package com.software.phoenix.model.request;

import lombok.Data;

@Data
public class SignUpRequest {
    private String login;
    private String password;
    private String passwordConfirmation;
    private String fullname;
    private String avatarUrl;
}