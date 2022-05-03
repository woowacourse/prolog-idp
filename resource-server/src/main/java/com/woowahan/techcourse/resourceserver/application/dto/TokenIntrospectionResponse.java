package com.woowahan.techcourse.resourceserver.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TokenIntrospectionResponse {

    private boolean active;
}
