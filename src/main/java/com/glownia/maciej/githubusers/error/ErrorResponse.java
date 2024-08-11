package com.glownia.maciej.githubusers.error;

public record ErrorResponse(
        Integer status,
        String message
) {
}
