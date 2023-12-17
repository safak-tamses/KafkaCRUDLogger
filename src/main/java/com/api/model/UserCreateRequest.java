package com.api.model;

public record UserCreateRequest(
        String name,
        String surname,
        String email
) {
}
