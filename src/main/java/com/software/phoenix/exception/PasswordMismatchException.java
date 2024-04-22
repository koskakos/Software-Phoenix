package com.software.phoenix.exception;

public class PasswordMismatchException extends RuntimeException {
    public PasswordMismatchException() {
        super("Password and password confirmation do not match");
    }
}
