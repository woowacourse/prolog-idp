package wooteco.idp.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByEmailAndPassword(String email, String password);
}
