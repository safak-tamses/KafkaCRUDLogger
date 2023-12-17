package com.api.error;

public class UserUpdateException extends RuntimeException{
    public UserUpdateException() {
        super("Failed to update the user.");
    }
}
