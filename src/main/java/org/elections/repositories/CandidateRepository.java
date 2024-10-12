package org.elections.repositories;



import org.elections.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    boolean existsByName(String name);

    List<Candidate> findByPositionId(Long positionId);

    Candidate findById(long id);
}
