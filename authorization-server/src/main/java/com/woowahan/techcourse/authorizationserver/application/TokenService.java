package com.woowahan.techcourse.authorizationserver.application;

import com.woowahan.techcourse.authorizationserver.application.dto.IntrospectionRequest;
import com.woowahan.techcourse.authorizationserver.application.dto.IntrospectionResponse;
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

    public String createAccessToken(AccessTokenRequest accessTokenRequest) {
        Code code = findAuthorizationCode(accessTokenRequest);
        validateClientSecret(accessTokenRequest);
        Account account = accountService.findById(code.getAccountId());
        return jwtTokenProvider.createAccessToken(account);
    }

    private Code findAuthorizationCode(AccessTokenRequest accessTokenRequest) {
        Code code = codeService.findByCode(accessTokenRequest.getCode());
        code.checkExpireTime();
        // delete authorization code from database
        return code;
    }

    private void validateClientSecret(AccessTokenRequest accessTokenRequest) {
        Registration registration = registrationService.findByClientId(accessTokenRequest.getClient_id());
        String clientSecret = accessTokenRequest.getClient_secret();
        registration.validate(clientSecret);
    }

    public IntrospectionResponse introspect(IntrospectionRequest introspectionRequest) {
        boolean active = jwtTokenProvider.validateAccessToken(introspectionRequest.getToken());
        return new IntrospectionResponse(active);
    }
}
