package com.woowahan.techcourse.authorizationserver.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.woowahan.techcourse.authorizationserver.application.dto.AccessTokenRequest;
import com.woowahan.techcourse.authorizationserver.application.dto.AuthorizationCodeRequest;
import com.woowahan.techcourse.authorizationserver.infrastructure.RandomGenerator;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String clientId;

    @Column(nullable = false)
    private String clientSecret;

    @Column
    private String clientName;

    @Column
    private String homepageUri;

    @Column
    private String redirectUri;

    @Column
    private String scopes;

    public Registration(String clientName, String homepageUri, String redirectUri, String scopes) {
        this.clientId = RandomGenerator.generateKey(20);
        this.clientSecret = RandomGenerator.generateKey(30);
        this.clientName = clientName;
        this.homepageUri = homepageUri;
        this.redirectUri = redirectUri;
        this.scopes = scopes;
    }

    public void validateAuthorizationCodeRequest(AuthorizationCodeRequest authorizationCodeRequest) {
        if (!redirectUri.equals(authorizationCodeRequest.getRedirect_uri())) {
            // TODO: custom exception
            throw new RuntimeException();
        }
    }

    public void validate(AccessTokenRequest accessTokenRequest) {
        if (!clientSecret.equals(accessTokenRequest.getClient_secret())) {
            // TODO: custom exception
            throw new RuntimeException();
        }
    }
}
