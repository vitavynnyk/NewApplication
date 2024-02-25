package com.example.newapplication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/github")
public class GitHubController {

    private final RestTemplate restTemplate;

    @GetMapping("/repos/{username}")
    public ResponseEntity<?> getRepositories(@PathVariable String username) {
        try {
            String GITHUB_API_URL = "https://api.github.com";
            String url = GITHUB_API_URL + "/users/" + username + "/repos";
            Repository[] repositories = restTemplate.getForObject(url, Repository[].class);

            assert repositories != null;

            List<RepositoryInfo> result = new ArrayList<>();

            for (Repository repository : repositories) {
                if (!repository.isFork()) {

                    result.add(RepositoryInfo.builder()
                            .repositoryName(repository.getName())
                            .ownerLogin(repository.getOwner().getLogin())
                            .branches(List.of(Objects.requireNonNull(restTemplate.getForObject(GITHUB_API_URL + "/repos"
                                            + "/" + username + "/" + repository.getName() + "/branches",
                                    RepositoryInfo.BranchInfo[].class))))
                            .build());
                }
            }
            return ResponseEntity.ok(result);
        } catch (HttpClientErrorException.NotFound notFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(new ErrorResponse(String.valueOf(HttpStatus.NOT_FOUND.value()),
                            String.format("User with username='%s' is not found", username)));
        }
    }

}

