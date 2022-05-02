package com.woowahan.techcourse.authorizationserver.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.woowahan.techcourse.authorizationserver.domain.Code;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CodeResponse {

    private String code;
    private String redirectUri;

    public static CodeResponse of(Code code, String redirectUri) {
        return new CodeResponse(code.getValue(), redirectUri);
    }
}
