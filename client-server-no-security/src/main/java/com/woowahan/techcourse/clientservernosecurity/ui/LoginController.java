package com.woowahan.techcourse.clientservernosecurity.ui;

import com.woowahan.techcourse.clientservernosecurity.application.LoginService;
import com.woowahan.techcourse.clientservernosecurity.application.dto.BackendRequest;
import com.woowahan.techcourse.clientservernosecurity.application.dto.LoginRequest;
import com.woowahan.techcourse.clientservernosecurity.application.dto.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/woowacourse/callback")
    public ResponseEntity<String> frontendCallBack(@ModelAttribute BackendRequest backendRequest) {
        System.out.println(backendRequest.getCode());
        System.out.println(backendRequest.getState());
        return ResponseEntity.ok("it's time for backend");
    }

    @PostMapping("/login")
    public ResponseEntity<String> retrieveAccessToken(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = loginService.login(loginRequest);
        System.out.println("done");
        return ResponseEntity.ok("done");
    }
}
