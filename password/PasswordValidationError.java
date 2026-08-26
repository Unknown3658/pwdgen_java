package com.pwdgen.password;

public class PasswordValidationError extends RuntimeException {
    public PasswordValidationError(String message) {
        super(message);
    }
    
    public PasswordValidationError(String message, Throwable cause) {
        super(message, cause);
    }
}
