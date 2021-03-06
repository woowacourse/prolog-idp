package com.woowahan.techcourse.authorizationserver.utils;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import com.woowahan.techcourse.authorizationserver.application.RegistrationService;
import com.woowahan.techcourse.authorizationserver.application.dto.RegistrationRequest;

@Profile({"test"})
@Configuration
public class DataLoaderApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private RegistrationService registrationService;

    public DataLoaderApplicationListener(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        registrationService.createRegistration(new RegistrationRequest(
                "prolog",
                "https://prolog.techcourse.co.kr",
                "https://prolog.techcourse.co.kr/login/callback",
                ""
        ));

        registrationService.createRegistration(new RegistrationRequest(
                "techcourse",
                "https://techcourse.co.kr",
                "https://techcourse.co.kr/login/callback",
                ""
        ));
    }
}
