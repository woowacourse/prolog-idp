package com.woowahan.techcourse.authorizationserver.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CodeRepository extends JpaRepository<Code, Long> {

    Optional<Code> findByValue(String code);
}
