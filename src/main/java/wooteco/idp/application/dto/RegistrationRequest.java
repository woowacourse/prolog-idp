package wooteco.idp.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegistrationRequest {

    private String clientName;
    private String homepageUri;
    private String redirectUri;
    private String scopes;
}
