package wooteco.idp.ui;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wooteco.idp.application.RegistrationService;
import wooteco.idp.application.dto.RegistrationRequest;
import wooteco.idp.application.dto.RegistrationResponse;

@RestController
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/registrations")
    public ResponseEntity<RegistrationResponse> create(@RequestBody RegistrationRequest registrationRequest) {
        return ResponseEntity.ok(registrationService.createRegistration(registrationRequest));
    }

    @GetMapping("/registrations/{id}")
    public ResponseEntity<RegistrationResponse> findRegistration(@PathVariable Long id) {
        return ResponseEntity.ok(registrationService.findById(id));
    }
}
