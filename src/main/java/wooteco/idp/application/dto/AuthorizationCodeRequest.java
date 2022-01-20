package wooteco.idp.application.dto;

import lombok.Getter;

@Getter
public class AuthorizationCodeRequest {

    private String responseType;
    private String clientId;
    private String state;
    private String redirectUri;
    private String scope;

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public void setClient_id(String clientId) {
        this.clientId = clientId;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setRedirect_uri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
