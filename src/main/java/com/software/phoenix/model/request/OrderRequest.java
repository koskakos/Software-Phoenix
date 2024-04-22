package com.software.phoenix.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderRequest {
    @NotEmpty(message = "The product is required.")
    private String product;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be greater than 0")
    private Integer quantity;

    @NotEmpty(message = "The address is required.")
    private String address;

    @NotEmpty(message = "The phone number is required.")
    private String phoneNumber;
}
