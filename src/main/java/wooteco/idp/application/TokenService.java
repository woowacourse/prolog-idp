package wooteco.idp.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import wooteco.idp.application.dto.TokenRequest;
import wooteco.idp.application.dto.TokenResponse;
import wooteco.idp.application.dto.github.GithubAccessTokenResponse;
import wooteco.idp.application.dto.github.GithubProfileResponse;
import wooteco.idp.domain.Account;
import wooteco.idp.domain.Registration;
import wooteco.idp.exception.GithubApiFailException;
import wooteco.idp.infrastructure.JwtTokenProvider;

@Service
@AllArgsConstructor
public class TokenService {
    private GithubClient githubClient;
    private AccountService accountService;
    private RegistrationService registrationService;
    private JwtTokenProvider jwtTokenProvider;

    public TokenResponse createToken(TokenRequest tokenRequest) {
        GithubAccessTokenResponse githubAccessTokenResponse = githubClient.getAccessTokenFromGithub(tokenRequest.getCode());
        if (githubAccessTokenResponse.getAccessToken() == null) {
            throw new GithubApiFailException();
        }

        GithubProfileResponse githubProfile = githubClient.getGithubProfileFromGithub(githubAccessTokenResponse.getAccessToken());
        Account account = accountService.findOrCreateMember(githubProfile);

        String idToken = jwtTokenProvider.createIdToken(account);

        Registration registration = registrationService.findByClientId(tokenRequest);
        String accessToken = jwtTokenProvider.createAccessToken(registration, account);

        return new TokenResponse(idToken, accessToken, "");
    }
}
