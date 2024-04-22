package com.software.phoenix.model.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequest {
    @Size(min = 2, max = 50, message = "The length of full name must be between 2 and 50 characters.")
    private String fullname;
    private String avatarUrl;
}
