package wooteco.idp.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TokenRequest {

    private String code;
    private String clientId;
    private String redirectUrl;

}
