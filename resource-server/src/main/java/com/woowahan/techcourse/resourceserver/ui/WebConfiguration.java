package com.woowahan.techcourse.resourceserver.ui;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final TokenIntrospectionInterceptor tokenIntrospectionInterceptor;

    public WebConfiguration(TokenIntrospectionInterceptor tokenIntrospectionInterceptor) {
        this.tokenIntrospectionInterceptor = tokenIntrospectionInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenIntrospectionInterceptor);
    }
}
