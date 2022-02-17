package wooteco.idp.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessTokenRequest {

    private String grant_type;
    private String code;
    private String redirect_url;
    private String client_id;
    private String client_secret;
}
