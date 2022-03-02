package com.woowahan.techcourse.authorizationserver.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.woowahan.techcourse.authorizationserver.application.dto.RegistrationRequest;
import com.woowahan.techcourse.authorizationserver.application.dto.RegistrationResponse;
import com.woowahan.techcourse.authorizationserver.domain.Registration;
import com.woowahan.techcourse.authorizationserver.domain.RegistrationRepository;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final RegistrationRepository registrationRepository;

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

