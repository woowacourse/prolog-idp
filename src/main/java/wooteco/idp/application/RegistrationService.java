package wooteco.idp.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import wooteco.idp.application.dto.TokenRequest;
import wooteco.idp.domain.Registration;
import wooteco.idp.domain.RegistrationRepository;

@AllArgsConstructor
@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;

    public Registration findByClientId(TokenRequest tokenRequest) {
        return registrationRepository.findByClientId(tokenRequest.getClientId()).orElseThrow(RuntimeException::new);
    }
}
