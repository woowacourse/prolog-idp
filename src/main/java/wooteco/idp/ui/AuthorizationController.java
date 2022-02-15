package wooteco.idp.ui;

import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import wooteco.idp.application.TokenService;
import wooteco.idp.application.dto.AuthorizationCodeRequest;
import wooteco.idp.application.dto.LoginRequest;

@Controller
@AllArgsConstructor
public class AuthorizationController {

    private final TokenService tokenService;

    @GetMapping("/oauth/authorize")
    public String oauthAuthorize(@ModelAttribute AuthorizationCodeRequest authorizationCodeRequest, HttpSession session) {
        session.setAttribute("authorizationCodeRequest", authorizationCodeRequest);
        return "login";
    }

    @PostMapping("/authenticate")
    public String authenticate(@ModelAttribute LoginRequest loginRequest, HttpSession session) {
        AuthorizationCodeRequest request =
            (AuthorizationCodeRequest) session.getAttribute("authorizationCodeRequest");
        String authorizationCode = tokenService.createAuthorizationCode(loginRequest);
        return String.format(
            "redirect:%s?code=%s&state=%s",
            request.getRedirect_uri(),
            authorizationCode,
            request.getState()
        );
    }
}
