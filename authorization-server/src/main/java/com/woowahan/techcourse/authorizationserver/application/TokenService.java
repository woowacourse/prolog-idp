package com.woowahan.techcourse.authorizationserver.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.woowahan.techcourse.authorizationserver.application.dto.AccessTokenRequest;
import com.woowahan.techcourse.authorizationserver.domain.Account;
import com.woowahan.techcourse.authorizationserver.domain.Code;
import com.woowahan.techcourse.authorizationserver.domain.Registration;
import com.woowahan.techcourse.authorizationserver.infrastructure.JwtTokenProvider;

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
