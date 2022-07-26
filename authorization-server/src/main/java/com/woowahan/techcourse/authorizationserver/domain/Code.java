package com.woowahan.techcourse.authorizationserver.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.woowahan.techcourse.authorizationserver.infrastructure.RandomGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String value;

    @Column
    private String registrationId;

    @Column
    private String redirectUri;

    @Column
    private Long accountId;

    @Column
    private LocalDateTime expireTime;

    public Code(String registrationId, String redirectUri, Long accountId) {
        this.value = RandomGenerator.generateKey(100);
        this.registrationId = registrationId;
        this.redirectUri = redirectUri;
        this.accountId = accountId;
        this.expireTime = LocalDateTime.now().plusSeconds(60);
    }

    public void checkExpireTime() {
        if (LocalDateTime.now().isAfter(this.expireTime)) {
            // TODO: detailed exception
            throw new RuntimeException();
        }
    }
}
