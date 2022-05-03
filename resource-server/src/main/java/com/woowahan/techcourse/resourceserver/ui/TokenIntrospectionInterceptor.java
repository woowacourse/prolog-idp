package com.woowahan.techcourse.resourceserver.ui;

import com.woowahan.techcourse.resourceserver.application.dto.TokenIntrospectionResponse;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.HandlerInterceptor;
import reactor.core.publisher.Mono;

@Component
public class TokenIntrospectionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        WebClient client = authorizationClient();
        Map<String, String> requestBody = createIntrospectionRequestBody(request);
        TokenIntrospectionResponse tokenIntrospectionResponse = introspectToken(client, requestBody);
        return tokenIntrospectionResponse.isActive();
    }

    private WebClient authorizationClient() {
        return WebClient.builder()
            .baseUrl("http://localhost:8081")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
    }

    private Map<String, String> createIntrospectionRequestBody(HttpServletRequest request) {
        String authorizationValue = request.getHeader("Authorization");
        String accessToken = authorizationValue.substring(7);
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", accessToken);
        return requestBody;
    }

    private TokenIntrospectionResponse introspectToken(WebClient client, Map<String, String> requestBody) {
        return client.post()
            .uri("/oauth/token_info")
            .body(BodyInserters.fromValue(requestBody))
            .exchangeToMono(resp -> {
                if (resp.statusCode().equals(HttpStatus.OK)) {
                    return resp.bodyToMono(TokenIntrospectionResponse.class);
                } else {
                    return resp.createException().flatMap(Mono::error);
                }
            })
            .block();
    }
}
