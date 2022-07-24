package com.woowahan.techcourse.authorizationserver.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class AccessTokenResponse {

    private String access_token;
    private String token_type;
//    private int expires_in;
//    private String refresh_token;
//    private String scope;
}
