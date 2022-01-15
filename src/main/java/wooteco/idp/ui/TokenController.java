package wooteco.idp.ui;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import wooteco.idp.application.AccountService;
import wooteco.idp.application.dto.AuthorizationCodeRequest;
import wooteco.idp.application.dto.LoginRequest;

@Controller
public class TokenController {

    private final AccountService accountService;

    public TokenController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/oauth/authorize")
    public String oauthAuthorize(AuthorizationCodeRequest authorizationCodeRequest, HttpSession session) {
        session.setAttribute("authorizationCodeRequest", authorizationCodeRequest);
        return "login";
    }

    @PostMapping("/authenticate")
    public String authenticate(LoginRequest loginRequest, HttpSession session) {
        AuthorizationCodeRequest authorizationCodeRequest =
            (AuthorizationCodeRequest) session.getAttribute("authorizationCodeRequest");
        String code = accountService.authenticate(loginRequest);
        return String.format(
            "redirect:%s?code=%s&state=%s",
            authorizationCodeRequest.getRedirectUri(),
            code,authorizationCodeRequest.getState()
        );
    }
}
