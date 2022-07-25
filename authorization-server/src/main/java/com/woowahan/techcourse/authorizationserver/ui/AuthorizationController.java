package com.woowahan.techcourse.authorizationserver.ui;

import com.woowahan.techcourse.authorizationserver.application.RegistrationService;
import com.woowahan.techcourse.authorizationserver.application.dto.UserResponse;
import com.woowahan.techcourse.authorizationserver.application.dto.AccessTokenResponse;
import com.woowahan.techcourse.authorizationserver.application.dto.IntrospectionRequest;
import com.woowahan.techcourse.authorizationserver.application.dto.IntrospectionResponse;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.woowahan.techcourse.authorizationserver.application.AccountService;
import com.woowahan.techcourse.authorizationserver.application.CodeService;
import com.woowahan.techcourse.authorizationserver.application.TokenService;
import com.woowahan.techcourse.authorizationserver.application.dto.AccessTokenRequest;
import com.woowahan.techcourse.authorizationserver.application.dto.AuthorizationCodeRequest;
import com.woowahan.techcourse.authorizationserver.application.dto.LoginRequest;
import com.woowahan.techcourse.authorizationserver.application.dto.LoginResponse;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
public class AuthorizationController {

    private final RegistrationService registrationService;
    private final AccountService accountService;
    private final CodeService codeService;
    private final TokenService tokenService;

    @GetMapping("/oauth/authorize")
    public String oauthAuthorize(@ModelAttribute AuthorizationCodeRequest authorizationCodeRequest,
                                 HttpSession session) {
        registrationService.validate(authorizationCodeRequest);
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
    public ResponseEntity<AccessTokenResponse> issueAccessToken(@ModelAttribute AccessTokenRequest accessTokenRequest) {
        AccessTokenResponse accessTokenResponse = tokenService.createAccessToken(accessTokenRequest);
        return ResponseEntity.ok()
            .cacheControl(CacheControl.noStore())
            .body(accessTokenResponse);
    }

    @PostMapping("/oauth/token_info")
    public ResponseEntity<IntrospectionResponse> tokenIntrospect(
        @RequestBody IntrospectionRequest introspectionRequest) {
        IntrospectionResponse introspectionResponse = tokenService.introspect(introspectionRequest);
        return ResponseEntity.ok(introspectionResponse);
    }

    @GetMapping("/users/me")
    public ResponseEntity<UserResponse> findUser() {
        return ResponseEntity.ok(new UserResponse("pkeugine"));
    }
}
