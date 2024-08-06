package com.challengeApp.ChallengeApp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge,Long> {
    Optional<Challenge> findByMonthIgnoreCase(String month);
}
