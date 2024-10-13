package org.elections.repositories;



import org.elections.models.Candidate;
import org.elections.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    boolean existsByName(String name);

    List<Candidate> findByPosition(Position position);

    Candidate findById(long id);
}
