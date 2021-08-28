package wooteco.idp.ui;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import wooteco.idp.application.dto.TokenResponse;
import wooteco.idp.domain.Registration;
import wooteco.idp.domain.RegistrationRepository;
import wooteco.idp.utils.GithubResponses;

import java.util.HashMap;
import java.util.Map;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TokenAcceptanceTest {
    public static final String CLIENT_ID = "clientId";
    public static final String REDIRECT_URL = "redirectUrl";
    @LocalServerPort
    private int port;

    @Autowired
    private RegistrationRepository registrationRepository;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;

        registrationRepository.save(new Registration(
                null,
                CLIENT_ID,
                "clientSecret",
                REDIRECT_URL,
                "",
                ""
        ));
    }

    @Test
    void getToken() {
        // when
        Map<String, String> params = new HashMap<>();
        params.put("code", GithubResponses.브라운.getCode());
        params.put("clientId", CLIENT_ID);
        params.put("redirectUrl", REDIRECT_URL);

        ExtractableResponse<Response> response = RestAssured
                .given().log().all()
                .body(params)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/token")
                .then().log().all().extract();

        // then
        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());

        TokenResponse tokenResponse = response.as(TokenResponse.class);
        Assertions.assertThat(tokenResponse.getIdToken()).isNotNull();
        Assertions.assertThat(tokenResponse.getAccessToken()).isNotNull();
        Assertions.assertThat(tokenResponse.getRefreshToken()).isNotNull();
    }
}
