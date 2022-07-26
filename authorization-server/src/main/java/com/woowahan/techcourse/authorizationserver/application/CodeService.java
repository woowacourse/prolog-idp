package com.woowahan.techcourse.authorizationserver.application;

import com.woowahan.techcourse.authorizationserver.application.dto.AuthorizationCodeRequest;
import com.woowahan.techcourse.authorizationserver.application.dto.LoginResponse;
import com.woowahan.techcourse.authorizationserver.domain.Account;
import com.woowahan.techcourse.authorizationserver.domain.Code;
import com.woowahan.techcourse.authorizationserver.domain.CodeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CodeService {

    private final AccountService accountService;
    private final CodeRepository codeRepository;

    public String createAuthorizationCode(LoginResponse loginResponse, AuthorizationCodeRequest authorizationCodeRequest) {
        Account account = accountService.findById(loginResponse.getId());
        Code authorizationCode = createNewCode(
            authorizationCodeRequest.getClient_id(),
            authorizationCodeRequest.getRedirect_uri(),
            account.getId()
        );
        return authorizationCode.getValue();
    }

    public Code createNewCode(String registrationId, String redirectUri, Long accountId) {
        return codeRepository.save(new Code(registrationId, redirectUri, accountId));
    }

    public Code findByCode(String value) {
        return codeRepository.findByValue(value).orElseThrow(RuntimeException::new);
    }
}
