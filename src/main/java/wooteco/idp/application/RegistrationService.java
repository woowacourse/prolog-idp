package wooteco.idp.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import wooteco.idp.application.dto.RegistrationRequest;
import wooteco.idp.application.dto.RegistrationResponse;
import wooteco.idp.application.dto.TokenRequest;
import wooteco.idp.domain.Registration;
import wooteco.idp.domain.RegistrationRepository;

@AllArgsConstructor
@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;

    public Registration findByClientId(String clientId) {
        return registrationRepository.findByClientId(clientId).orElseThrow(RuntimeException::new);
    }

    public RegistrationResponse createRegistration(RegistrationRequest registrationRequest) {
        Registration persistRegistration = registrationRepository.save(new Registration(
                registrationRequest.getClientName(),
                registrationRequest.getHomepageUri(),
                registrationRequest.getRedirectUri(),
                registrationRequest.getScopes()
        ));
        return RegistrationResponse.of(persistRegistration);
    }

    public RegistrationResponse findById(Long id) {
        Registration persistRegistration = registrationRepository.findById(id).orElseThrow(RuntimeException::new);
        return RegistrationResponse.of(persistRegistration);
    }
}
