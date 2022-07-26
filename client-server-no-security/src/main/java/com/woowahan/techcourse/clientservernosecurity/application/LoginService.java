package com.woowahan.techcourse.clientservernosecurity.application;

import com.woowahan.techcourse.clientservernosecurity.application.dto.LoginRequest;
import com.woowahan.techcourse.clientservernosecurity.application.dto.LoginResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginService {

    @Value("${woowacourse.client.id}")
    private String clientId;
    @Value("${woowacourse.client.secret}")
    private String clientSecret;
    @Value("${woowacourse.url.access-token}")
    private String url;

    public LoginResponse login(LoginRequest loginRequest) {
        String authorizationCode = loginRequest.getCode();
        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.postForObject();

        return null;
    }
}
