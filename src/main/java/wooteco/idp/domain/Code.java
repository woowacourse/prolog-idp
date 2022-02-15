package wooteco.idp.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wooteco.idp.infrastructure.RandomGenerator;

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
    private Long registrationId;

    @Column
    private String redirectUri;

    @Column
    private Long accountId;

    @Column
    private LocalDateTime expireTime;

    public Code(Long registrationId, String redirectUri, Long accountId) {
        this.value = RandomGenerator.generateKey(10);
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
