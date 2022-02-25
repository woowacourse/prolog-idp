package wooteco.idp.ui;

import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import wooteco.idp.application.AccountService;
import wooteco.idp.application.CodeService;
import wooteco.idp.application.TokenService;
import wooteco.idp.application.dto.AccessTokenRequest;
import wooteco.idp.application.dto.AuthorizationCodeRequest;
import wooteco.idp.application.dto.LoginRequest;
import wooteco.idp.application.dto.LoginResponse;

@Controller
@AllArgsConstructor
public class AuthorizationController {

    private final CodeService codeService;
    private final AccountService accountService;
    private final TokenService tokenService;

    @GetMapping("/oauth/authorize")
    public String oauthAuthorize(@ModelAttribute AuthorizationCodeRequest authorizationCodeRequest, HttpSession session) {
        session.setAttribute("authorizationCodeRequest", authorizationCodeRequest);
        return "login";
    }

    @PostMapping("/authenticate")
    public String authenticate(@ModelAttribute LoginRequest loginRequest, HttpSession session) {
        // give login cookie
        LoginResponse loginResponse = accountService.authorize(loginRequest);
        session.setAttribute("loginResponse", loginResponse);
        return "authorization";
    }

    @PostMapping("/authorize/granted")
    public String grantAuthorize(HttpSession session) {
        // receive "granted"
        // receive request id to prevent csrf
        AuthorizationCodeRequest authorizationCodeRequest =
            (AuthorizationCodeRequest) session.getAttribute("authorizationCodeRequest");
        LoginResponse loginResponse = (LoginResponse) session.getAttribute("loginResponse");
        String authorizationCode = codeService.createAuthorizationCode(loginResponse, authorizationCodeRequest);
        return String.format(
            "redirect:%s?code=%s&state=%s",
            authorizationCodeRequest.getRedirect_uri(),
            authorizationCode,
            authorizationCodeRequest.getState()
        );
    }

    @PostMapping("/oauth/token")
    public ResponseEntity<String> issueAccessToken(@ModelAttribute AccessTokenRequest accessTokenRequest) {
        String accessToken = tokenService.createToken(accessTokenRequest);
        return ResponseEntity.ok(accessToken);
    }
}
