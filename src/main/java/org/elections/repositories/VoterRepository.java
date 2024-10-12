package org.elections.repositories;

import org.elections.models.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterRepository extends JpaRepository<Voter, Long> {
    boolean existsByName(String name);
}
