package wooteco.idp.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import wooteco.idp.application.dto.LoginRequest;
import wooteco.idp.application.dto.LoginResponse;
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

    public LoginResponse authorize(LoginRequest loginRequest) {
        Account account = findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        return new LoginResponse(account.getId(), account.getEmail(), account.getName(), account.getNickname());
    }

}
