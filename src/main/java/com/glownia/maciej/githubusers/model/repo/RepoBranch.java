package com.glownia.maciej.githubusers.model.repo;

public record RepoBranch(
        String name,
        Commit commit
) {
}
