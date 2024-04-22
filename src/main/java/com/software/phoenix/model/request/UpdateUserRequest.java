package com.software.phoenix.model.request;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String fullname;
    private String avatarUrl;
}
