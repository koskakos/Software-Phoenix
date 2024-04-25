package com.software.phoenix.controller;

import com.software.phoenix.model.request.UpdateUserRequest;
import com.software.phoenix.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Update authenticated user")
    @PutMapping("")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UpdateUserRequest updateUserRequest) {
        return ResponseEntity.ok(userService.updateUser(updateUserRequest));
    }
    @Operation(summary = "Get authenticated user")
    @GetMapping("")
    public ResponseEntity<?> getAuthenticatedUser() {
        return ResponseEntity.ok(userService.getAuthenticatedUser());
    }


    @Operation(summary = "Get all orders of an authenticated user")
    @GetMapping("/orders")
    public ResponseEntity<?> getAllOrders() {
        return ResponseEntity.ok(userService.getAllOrders(userService.getAuthenticatedUser()));
    }
}
