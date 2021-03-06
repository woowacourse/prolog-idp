package com.woowahan.techcourse.authorizationserver.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.woowahan.techcourse.authorizationserver.domain.Registration;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegistrationResponse {

    private Long id;
    private String clientId;
    private String clientSecret;
    private String clientName;
    private String homepageUri;
    private String redirectUri;
    private String scopes;

    public static RegistrationResponse of(Registration registration) {
        return new RegistrationResponse(
                registration.getId(),
                registration.getClientId(),
                registration.getClientSecret(),
                registration.getClientName(),
                registration.getHomepageUri(),
                registration.getRedirectUri(),
                registration.getScopes()
        );
    }
}
