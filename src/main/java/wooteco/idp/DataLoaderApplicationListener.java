package wooteco.idp;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import wooteco.idp.application.RegistrationService;
import wooteco.idp.application.dto.RegistrationRequest;

@Profile({"none"})
@AllArgsConstructor
@Configuration
public class DataLoaderApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private RegistrationService registrationService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        registrationService.createRegistration(new RegistrationRequest(
                "prolog",
                "http://localhost:8080",
                "http://localhost:8080/login/callback",
                ""
        ));
    }
}
