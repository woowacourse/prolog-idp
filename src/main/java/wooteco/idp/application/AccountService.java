package wooteco.idp.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import wooteco.idp.application.dto.github.GithubProfileResponse;
import wooteco.idp.domain.Account;
import wooteco.idp.domain.AccountRepository;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account findOrCreateMember(GithubProfileResponse githubProfile) {
        return accountRepository.findByGithubId(githubProfile.getGithubId())
                .orElseGet(() -> accountRepository.save(githubProfile.toAccount()));
    }

    public Account findByCode(String code) {
        return null;
    }

    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email).orElseThrow(RuntimeException::new);
    }
}
