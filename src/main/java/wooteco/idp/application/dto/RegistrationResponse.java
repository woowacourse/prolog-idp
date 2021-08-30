package wooteco.idp.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wooteco.idp.domain.Registration;

@NoArgsConstructor
@AllArgsConstructor
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
