package wooteco.idp.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByGithubId(String githubId);

    Optional<Account> findByEmail(String email);
}
