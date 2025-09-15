package com.glownia.maciej.githubusers.model.repo;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RepoDetails(
        @JsonProperty("name") String repoName,
        @JsonProperty("owner") RepoOwner repoOwner,
        @JsonProperty("fork") boolean isFork
) {
}