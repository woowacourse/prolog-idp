package com.woowahan.techcourse.authorizationserver.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.woowahan.techcourse.authorizationserver.application.dto.LoginRequest;
import com.woowahan.techcourse.authorizationserver.application.dto.LoginResponse;
import com.woowahan.techcourse.authorizationserver.domain.Account;
import com.woowahan.techcourse.authorizationserver.domain.AccountRepository;

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
