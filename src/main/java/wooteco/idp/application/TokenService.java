package wooteco.idp.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import wooteco.idp.application.dto.TokenRequest;
import wooteco.idp.application.dto.TokenResponse;
import wooteco.idp.domain.Account;
import wooteco.idp.domain.Code;
import wooteco.idp.domain.Registration;
import wooteco.idp.infrastructure.JwtTokenProvider;

@Service
@AllArgsConstructor
public class TokenService {

    private final AccountService accountService;
    private final RegistrationService registrationService;
    private final CodeService codeService;
    private final JwtTokenProvider jwtTokenProvider;

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

        // TODO: detailed exception
        throw new RuntimeException();
    }
}
