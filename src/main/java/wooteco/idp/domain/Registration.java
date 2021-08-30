package wooteco.idp.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wooteco.idp.application.dto.TokenRequest;
import wooteco.idp.infrastructure.RandomGenerator;

import javax.persistence.*;

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

    public void validate(TokenRequest tokenRequest) {
        if (!clientSecret.equals(tokenRequest.getClientSecret())) {
            throw new RuntimeException();
        }
    }
}
