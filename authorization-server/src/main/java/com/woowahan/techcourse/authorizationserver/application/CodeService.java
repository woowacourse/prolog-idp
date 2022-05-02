package com.woowahan.techcourse.authorizationserver.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.woowahan.techcourse.authorizationserver.application.dto.AuthorizationCodeRequest;
import com.woowahan.techcourse.authorizationserver.application.dto.LoginResponse;
import com.woowahan.techcourse.authorizationserver.domain.Account;
import com.woowahan.techcourse.authorizationserver.domain.Code;
import com.woowahan.techcourse.authorizationserver.domain.CodeRepository;
import com.woowahan.techcourse.authorizationserver.domain.Registration;

@Service
@AllArgsConstructor
public class CodeService {

    private final AccountService accountService;
    private final RegistrationService registrationService;
    private final CodeRepository codeRepository;

    public String createAuthorizationCode(LoginResponse loginResponse, AuthorizationCodeRequest authorizationCodeRequest) {
        Account account = accountService.findById(loginResponse.getId());
        Registration registration = registrationService.findByClientId(authorizationCodeRequest.getClient_id());

        registration.validateAuthorizationCodeRequest(authorizationCodeRequest);
        Code authorizationCode = createNewCode(registration.getId(), registration.getRedirectUri(), account.getId());
        return authorizationCode.getValue();
    }

    public Code createNewCode(Long registrationId, String redirectUri, Long accountId) {
        return codeRepository.save(new Code(registrationId, redirectUri, accountId));
    }

    public Code findByCode(String value) {
        return codeRepository.findByValue(value).orElseThrow(RuntimeException::new);
    }
}
