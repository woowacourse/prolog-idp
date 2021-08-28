package wooteco.idp.ui;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import wooteco.idp.application.TokenService;
import wooteco.idp.application.dto.TokenRequest;
import wooteco.idp.application.dto.TokenResponse;

@RestController
@AllArgsConstructor
public class TokenController {
    private TokenService tokenService;

    @PostMapping("/token")
    public ResponseEntity<TokenResponse> token(@RequestBody TokenRequest tokenRequest) {
        return ResponseEntity.ok(tokenService.createToken(tokenRequest));
    }
}
