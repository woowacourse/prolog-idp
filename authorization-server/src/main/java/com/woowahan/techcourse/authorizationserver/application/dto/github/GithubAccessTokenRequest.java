package com.woowahan.techcourse.authorizationserver.application.dto.github;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GithubAccessTokenRequest {

    private String code;
    private String client_id;
    private String client_secret;
}
