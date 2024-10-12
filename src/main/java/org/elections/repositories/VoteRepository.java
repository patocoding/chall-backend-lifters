package org.elections.repositories;

import org.elections.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    boolean existsByVoterId(Long voterId);

    long countByCandidateId(Long candidateId);
}
