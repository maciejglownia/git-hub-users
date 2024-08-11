package com.glownia.maciej.githubusers.controller;

import com.glownia.maciej.githubusers.error.ErrorResponse;
import com.glownia.maciej.githubusers.model.response.GithubRepository;
import com.glownia.maciej.githubusers.service.GithubRepositoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GithubRepoController {

    private final GithubRepositoryService githubRepositoryService;

    public GithubRepoController(GithubRepositoryService githubRepositoryService) {
        this.githubRepositoryService = githubRepositoryService;
    }

    @GetMapping(value = "/users/{username}/repos")
    public ResponseEntity<?> getUserRepos(@PathVariable String username) {
        try {
            List<GithubRepository> repos = githubRepositoryService.getUserGithubDetails(username);
            return ResponseEntity.ok(repos);
        } catch (Exception e) {
            ErrorResponse response = new ErrorResponse(
                    HttpStatus.NOT_FOUND.value(),
                    e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}
