package com.woowahan.techcourse.authorizationserver.infrasturcture;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.woowahan.techcourse.authorizationserver.infrastructure.JwtTokenProvider;

@ActiveProfiles("test")
@SpringBootTest
class JwtTokenProviderTest {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Test
    void name() {
        String idToken = "eyJhbGciOiJIUzI1NiJ9.eyJwaG9uZS1udW1iZXIiOiIiLCJuYW1lIjoiIiwibmlja25hbWUiOiLrpZjshLHtmIQiLCJpc3MiOiJodHRwczovL2FjY291bnQudGVjaGNvdXJzZS5jby5rci9hdXRoIiwiZXhwIjoxNjMwODAzOTQxLCJpYXQiOjE2MzAxOTkxNDEsInBpY3R1cmUiOiJodHRwczovL2F2YXRhcnMuZ2l0aHVidXNlcmNvbnRlbnQuY29tL3UvNDYzMDg5NDk_dj00IiwiZW1haWwiOiIifQ.3UoDtQlnXyKHn-GOtILz9RpAxuHXqDzX3QYHMFKuO5c";
        jwtTokenProvider.validateToken("", idToken);
    }
}
