package com.glownia.maciej.githubusers.service;

import com.glownia.maciej.githubusers.model.repo.RepoBranch;
import com.glownia.maciej.githubusers.model.repo.RepoDetails;
import com.glownia.maciej.githubusers.model.dto.BranchDto;
import com.glownia.maciej.githubusers.model.response.GithubRepository;
import com.glownia.maciej.githubusers.utils.HttpEntityCreator;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.glownia.maciej.githubusers.utils.Constants.BRANCHES_PATH;
import static com.glownia.maciej.githubusers.utils.Constants.GITHUB_API_URL;

@Service
public class GithubBranchService {

    private final RestTemplate restTemplate;

    public GithubBranchService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<GithubRepository> getRepositories(String username, List<RepoDetails> notForkedRepos) {
        List<GithubRepository> repositories = new ArrayList<>();

        for (RepoDetails repoDetails : notForkedRepos) {
            List<BranchDto> branches = getBranches(username, repoDetails.repoName());
            GithubRepository githubRepository = new GithubRepository(repoDetails.repoName(), repoDetails.repoOwner().repoOwnerLogin(), branches);
            repositories.add(githubRepository);
        }
        return repositories;
    }

    public List<BranchDto> getBranches(String username, String repoName) {
        String url = UriComponentsBuilder.fromHttpUrl(GITHUB_API_URL)
                .path(BRANCHES_PATH)
                .buildAndExpand(username, repoName)
                .toUriString();

        ResponseEntity<RepoBranch[]> result = restTemplate
                .exchange(url, HttpMethod.GET, HttpEntityCreator.createHttpEntity(), RepoBranch[].class);

        return Arrays.stream(Objects.requireNonNull(result.getBody()))
                .map(branch -> new BranchDto(branch.name(), branch.commit().sha()))
                .toList();
    }
}