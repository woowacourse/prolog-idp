package wooteco.idp.ui;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wooteco.idp.application.TokenService;
import wooteco.idp.application.dto.CodeResponse;
import wooteco.idp.application.dto.TokenRequest;
import wooteco.idp.application.dto.TokenResponse;

@Controller
public class TokenController {
    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping("/login/authorize")
    public String githubCallback(@RequestParam(name = "code") String code, @RequestParam(name = "state") String clientId) {
        CodeResponse response = tokenService.createCode(code, clientId);
        return String.format("redirect:%s?code=%s", response.getRedirectUri(), response.getCode());
    }

    @ResponseBody
    @GetMapping("/login/token")
    public ResponseEntity<TokenResponse> generateToken(@RequestBody TokenRequest tokenRequest) {
        return ResponseEntity.ok(tokenService.createToken(tokenRequest));
    }

//    @ResponseBody
//    @PostMapping("/login/form")
//    public ResponseEntity<TokenResponse> generateTokenByForm(@RequestBody FormTokenRequest)
}
