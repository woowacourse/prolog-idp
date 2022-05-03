package com.woowahan.techcourse.authorizationserver.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class IntrospectionResponse {

    private Boolean active;
//    private String scope;
//    private String client_id;
//    private String username;
//    private String exp;
}
