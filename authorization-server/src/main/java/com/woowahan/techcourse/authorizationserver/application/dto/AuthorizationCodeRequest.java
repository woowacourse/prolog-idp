package com.woowahan.techcourse.authorizationserver.application.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizationCodeRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String response_type;
    private String client_id;
    private String state;
    private String redirect_uri;
    private String scope;
}
