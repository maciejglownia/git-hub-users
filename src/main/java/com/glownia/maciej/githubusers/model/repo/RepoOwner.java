package com.glownia.maciej.githubusers.model.repo;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RepoOwner(
        @JsonProperty("login") String repoOwnerLogin
) {
}