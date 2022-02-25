package wooteco.idp.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import wooteco.idp.domain.Account;
import wooteco.idp.domain.AccountRepository;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Account findByEmailAndPassword(String email, String password) {
        return accountRepository.findByEmailAndPassword(email, password).orElseThrow(RuntimeException::new);
    }
}
