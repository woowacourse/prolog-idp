package wooteco.idp.ui;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import wooteco.idp.application.dto.RegistrationResponse;
import wooteco.idp.application.dto.TokenResponse;
import wooteco.idp.utils.GithubResponses;

import java.util.HashMap;
import java.util.Map;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TokenAcceptanceTest {
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void getToken() {
        // given - Registration 등록을 하고
        RegistrationResponse registration1 = RestAssured
                .given().log().all()
                .when().get("/registrations/1")
                .then().log().all().extract().as(RegistrationResponse.class);

        RegistrationResponse registration2 = RestAssured
                .given().log().all()
                .when().get("/registrations/2")
                .then().log().all().extract().as(RegistrationResponse.class);

        String githubCode = GithubResponses.브라운.getCode();

        // when - 인증 서버에 로그인을 하면
        ExtractableResponse<Response> codeResponse = RestAssured
                .given().log().all()
                .redirects().follow(false)
                .when().get("/login/authorize?code={code}&state={state}", githubCode, registration1.getClientId())
                .then().log().all().extract();

        // then - 인증 서버 코드를 응답 받는다
        Assertions.assertThat(codeResponse.statusCode()).isEqualTo(HttpStatus.FOUND.value());
        Assertions.assertThat(codeResponse.header("Location")).isNotNull();
        String newCode = codeResponse.header("Location").split("code=")[1];

        // when - 토큰을 요청하면
        Map<String, String> params = new HashMap<>();
        params.put("code", newCode);
        params.put("clientId", registration1.getClientId());
        params.put("clientSecret", registration1.getClientSecret());
        params.put("redirectUrl", registration1.getRedirectUri());

        ExtractableResponse<Response> tokenResponse = RestAssured
                .given().log().all()
                .body(params)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/login/token")
                .then().log().all().extract();

        // then - 토근을 응답 받는다
        TokenResponse token = tokenResponse.as(TokenResponse.class);
        Assertions.assertThat(token.getIdToken()).isNotNull();
        Assertions.assertThat(token.getAccessToken()).isNotNull();
        Assertions.assertThat(token.getRefreshToken()).isNotNull();

        // when - ID 토큰으로 가지고 다른 서비스의 액세스 토큰을 요청하면
        Map<String, String> params2 = new HashMap<>();
        params2.put("idToken", token.getIdToken());
        params2.put("clientId", registration2.getClientId());
        params2.put("clientSecret", registration2.getClientSecret());
        params2.put("redirectUrl", registration2.getRedirectUri());

        ExtractableResponse<Response> anotherTokenResponse = RestAssured
                .given().log().all()
                .body(params2)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/login/token")
                .then().log().all().extract();

        // then - 다른 서비스의 액세스 토큰을 받는다
        TokenResponse anotherToken = anotherTokenResponse.as(TokenResponse.class);
        Assertions.assertThat(anotherToken.getIdToken()).isNotNull();
        Assertions.assertThat(anotherToken.getAccessToken()).isNotNull();
        Assertions.assertThat(anotherToken.getRefreshToken()).isNotNull();
    }
}
