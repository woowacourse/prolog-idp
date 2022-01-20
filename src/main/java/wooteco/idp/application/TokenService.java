package wooteco.idp.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import wooteco.idp.application.dto.CodeResponse;
import wooteco.idp.application.dto.TokenRequest;
import wooteco.idp.application.dto.TokenResponse;
import wooteco.idp.application.dto.github.GithubAccessTokenResponse;
import wooteco.idp.application.dto.github.GithubProfileResponse;
import wooteco.idp.domain.Account;
import wooteco.idp.domain.Code;
import wooteco.idp.domain.Registration;
import wooteco.idp.exception.GithubApiFailException;
import wooteco.idp.infrastructure.JwtTokenProvider;

@Service
@AllArgsConstructor
public class TokenService {

    private final GithubClient githubClient;
    private final AccountService accountService;
    private final RegistrationService registrationService;
    private final CodeService codeService;
    private final JwtTokenProvider jwtTokenProvider;

    public CodeResponse createCode(String code, String clientId) {
        GithubAccessTokenResponse githubAccessTokenResponse = githubClient.getAccessTokenFromGithub(code);
        if (githubAccessTokenResponse.getAccessToken() == null) {
            throw new GithubApiFailException();
        }

        GithubProfileResponse githubProfile = githubClient.getGithubProfileFromGithub(githubAccessTokenResponse.getAccessToken());
        Account account = accountService.findOrCreateMember(githubProfile);
        Registration registration = registrationService.findByClientId(clientId);

        return CodeResponse.of(codeService.createNewCode(registration.getId(), account.getId()), registration.getRedirectUri());
    }

    public TokenResponse createToken(TokenRequest tokenRequest) {
        Long accountId = extractAccountId(tokenRequest);
        Account account = accountService.findById(accountId);

        Registration registration = registrationService.findByClientId(tokenRequest.getClientId());
        registration.validate(tokenRequest);

        String idToken = jwtTokenProvider.createIdToken(account);
        String accessToken = jwtTokenProvider.createAccessToken(registration, account);

        return new TokenResponse(idToken, accessToken, "");
    }

    private Long extractAccountId(TokenRequest tokenRequest) {
        if (tokenRequest.getIdToken() != null && !tokenRequest.getIdToken().isEmpty()) {
            return Long.parseLong(jwtTokenProvider.extractSubject(tokenRequest.getIdToken()));
        }
        if (tokenRequest.getCode() != null && !tokenRequest.getCode().isEmpty()) {
            Code code = codeService.findByCode(tokenRequest.getCode());
            code.checkExpireTime();
            return code.getAccountId();
        }

        throw new RuntimeException();
    }
}
