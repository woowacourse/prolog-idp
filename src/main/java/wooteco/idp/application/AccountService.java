package wooteco.idp.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import wooteco.idp.application.dto.github.GithubProfileResponse;
import wooteco.idp.domain.Account;
import wooteco.idp.domain.AccountRepository;

@AllArgsConstructor
@Service
public class AccountService {

    private AccountRepository accountRepository;

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
}
