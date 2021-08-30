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
    private Long accountId;

    @Column
    private LocalDateTime expireTime;

    public Code(Long registrationId, Long accountId) {
        this.value = RandomGenerator.generateKey(10);
        this.registrationId = registrationId;
        this.accountId = accountId;
        this.expireTime = LocalDateTime.now().plusMinutes(20);
    }

    public void checkExpireTime() {
        if (LocalDateTime.now().isAfter(this.expireTime)) {
            throw new RuntimeException();
        }
    }
}