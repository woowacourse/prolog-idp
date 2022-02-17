package wooteco.idp.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import wooteco.idp.application.dto.AccessTokenRequest;
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

    public String createToken(AccessTokenRequest accessTokenRequest) {
        Code code = codeService.findByCode(accessTokenRequest.getCode());
        code.checkExpireTime();

        Account account = accountService.findById(code.getAccountId());
        Registration registration = registrationService.findByClientId(accessTokenRequest.getClient_id());
        registration.validate(accessTokenRequest);

        return jwtTokenProvider.createAccessToken(registration, account);
    }
}
