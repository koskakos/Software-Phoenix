package com.software.phoenix.model.request;

import lombok.Data;

@Data
public class SignInRequest {
    private String login;
    private String password;
}