package wooteco.idp.ui;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wooteco.idp.application.TokenService;
import wooteco.idp.application.dto.CodeResponse;
import wooteco.idp.application.dto.LoginRequest;
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

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/perform_login")
    public void performLogin(@RequestBody LoginRequest loginRequest) {

    }

    @ResponseBody
    @PostMapping("/oauth/authorize")
    public ResponseEntity<Void> oauthAuthorize(@RequestParam("response_type") String responseType,
                                               @RequestParam("client_id") String clientId,
                                               @RequestParam("state") String state,
                                               @RequestParam("redirect_uri") String redirectUri,
                                               @RequestParam("scope") String scope) {
        // The authorization server must first verify that the client_id in the request corresponds to a valid application.
        // If the request contains a redirect_uri parameter, the server must confirm it is a valid redirect URL for this application.
        // If there is no redirect_uri parameter in the request, and only one URL was registered, the server uses the redirect URL that was previously registered.
        // Otherwise, if no redirect URL is in the request, and no redirect URL has been registered, this is an error.
        // If the client_id is invalid, the server should reject the request immediately and display the error to the user rather than redirecting the user back to the application.

        // If the authorization server detects a problem with the redirect URL, it needs to inform the user of the problem instead of redirecting the user.
        // The server should only redirect the user to the redirect URL if the redirect URL has been registered. (avoid 'open redirector attack')


        // All other errors should be handled by redirecting the user to the redirect URL with an error code in the query string.


        // If the request is missing the response_type parameter, or the value of that parameter is anything besides code or token, the server can return an invalid_request error.
        // Since the authorization server may require clients to specify if they are public or confidential, it can reject authorization requests that aren’t allowed. For example, if the client specified they are a confidential client, the server can reject a request that uses the token grant type. When rejecting for this reason, use the error code unauthorized_client.
        // The authorization server should reject the request if there are scope values that it doesn’t recognize. In this case, the server can redirect to the callback URL with the invalid_scope error code.
        // The authorization server needs to store the “state” value (and PKCE values) for this request in order to include it in the authorization response. The server must not modify or make any assumptions about what the state value contains, since it is purely for the benefit of the client.
        return null;
    }
}
