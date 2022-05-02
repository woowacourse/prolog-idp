package com.woowahan.techcourse.resourceserver.ui;

import com.woowahan.techcourse.resourceserver.application.dto.ValidationResponse;
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
public class TokenValidationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String authorizationValue = request.getHeader("Authorization");
        String accessToken = authorizationValue.substring(7);

        System.out.println("checking...");
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("token", accessToken);
        WebClient client = WebClient.builder()
            .baseUrl("http://localhost:8081")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
        ValidationResponse validationResponse = client.post()
            .uri("/oauth/token_info")
            .body(BodyInserters.fromValue(bodyMap))
            .exchangeToMono(resp -> {
                if (resp.statusCode().equals(HttpStatus.OK)) {
                    return resp.bodyToMono(ValidationResponse.class);
                } else {
                    return resp.createException().flatMap(Mono::error);
                }
            })
            .block();
        System.out.println("checked!");
        return validationResponse.isActive();
    }
}
