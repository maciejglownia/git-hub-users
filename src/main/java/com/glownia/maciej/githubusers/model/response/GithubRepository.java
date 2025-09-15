package com.glownia.maciej.githubusers.model.response;

import com.glownia.maciej.githubusers.model.dto.BranchDto;

import java.util.List;

public record GithubRepository(
        String repositoryName,
        String ownerLogin,
        List<BranchDto> branches
) {
}
