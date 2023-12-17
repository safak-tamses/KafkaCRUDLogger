package com.api.error;

public class UserReadException extends RuntimeException{
    public UserReadException() {
        super("Failed to read the user.");
    }
}
