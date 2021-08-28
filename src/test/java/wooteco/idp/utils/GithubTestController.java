package wooteco.idp.utils;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wooteco.idp.application.dto.github.GithubAccessTokenRequest;
import wooteco.idp.application.dto.github.GithubAccessTokenResponse;
import wooteco.idp.application.dto.github.GithubProfileResponse;

@Profile("test")
@RestController
public class GithubTestController {

    @PostMapping("/github/login/oauth/access_token")
    public ResponseEntity<GithubAccessTokenResponse> accessToken(
            @RequestBody GithubAccessTokenRequest tokenRequest) {
        String accessToken = GithubResponses.findByCode(tokenRequest.getCode()).getAccessToken();
        GithubAccessTokenResponse response = new GithubAccessTokenResponse(accessToken, "", "", "");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/github/user")
    public ResponseEntity<GithubProfileResponse> user(
            @RequestHeader("Authorization") String authorization) {
        String accessToken = authorization.split(" ")[1];
        GithubResponses githubResponse = GithubResponses.findByToken(accessToken);
        GithubProfileResponse response = new GithubProfileResponse(githubResponse.getName(),
                githubResponse.getLogin(), githubResponse.getId(), githubResponse.getAvatarUrl());
        return ResponseEntity.ok(response);
    }
}
