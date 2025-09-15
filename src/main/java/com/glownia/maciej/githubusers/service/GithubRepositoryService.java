package com.glownia.maciej.githubusers.service;

import com.glownia.maciej.githubusers.model.repo.RepoDetails;
import com.glownia.maciej.githubusers.model.response.GithubRepository;
import com.glownia.maciej.githubusers.utils.HttpEntityCreator;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.glownia.maciej.githubusers.utils.Constants.GITHUB_API_URL;
import static com.glownia.maciej.githubusers.utils.Constants.USERNAME_PATH;

@Service
public class GithubRepositoryService {

    private final RestTemplate restTemplate;
    private final GithubBranchService branchService;

    public GithubRepositoryService(RestTemplate restTemplate, GithubBranchService branchService) {
        this.restTemplate = restTemplate;
        this.branchService = branchService;
    }

    public List<GithubRepository> getUserGithubDetails(String username) {
        String url = UriComponentsBuilder.fromHttpUrl(GITHUB_API_URL)
                .path(USERNAME_PATH)
                .buildAndExpand(username)
                .toUriString();

        ResponseEntity<RepoDetails[]> result = restTemplate
                .exchange(url, HttpMethod.GET, HttpEntityCreator.createHttpEntity(), RepoDetails[].class);

        List<RepoDetails> notForkedRepos = getNotForkedRepos(result);

        return branchService.getRepositories(username, notForkedRepos);
    }

    private static List<RepoDetails> getNotForkedRepos(ResponseEntity<RepoDetails[]> result) {
        return Arrays.stream(Objects.requireNonNull(result.getBody()))
                .filter(repo -> !repo.isFork())
                .collect(Collectors.toList());
    }
}