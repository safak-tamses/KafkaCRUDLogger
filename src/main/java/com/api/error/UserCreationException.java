package com.api.error;

public class UserCreationException extends RuntimeException{
    public UserCreationException() {
        super("Failed to create the user.");
    }
}
