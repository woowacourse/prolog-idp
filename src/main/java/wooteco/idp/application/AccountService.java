package wooteco.idp.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import wooteco.idp.application.dto.LoginRequest;
import wooteco.idp.application.dto.github.GithubProfileResponse;
import wooteco.idp.domain.Account;
import wooteco.idp.domain.AccountRepository;
import wooteco.idp.infrastructure.JwtTokenProvider;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public Account findOrCreateMember(GithubProfileResponse githubProfile) {
        return accountRepository.findByGithubId(githubProfile.getGithubId())
                .orElseGet(() -> accountRepository.save(githubProfile.toAccount()));
    }

    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email).orElseThrow(RuntimeException::new);
    }

    public String createAuthorizationCode(LoginRequest loginRequest) {
        Account account = findByEmail(loginRequest.getEmail());
        if (account.incorrectPassword(loginRequest.getPassword())) {
            throw new IllegalArgumentException("로그인에 실패했습니다.");
        }
        return jwtTokenProvider.generateAuthorizationCode();
    }
}
