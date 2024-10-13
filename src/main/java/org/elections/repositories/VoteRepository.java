package org.elections.repositories;

import org.elections.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByVoterIdAndCandidatePositionId(Long voterId, Long positionId);

    Long countByCandidateId(Long candidateId);
}
