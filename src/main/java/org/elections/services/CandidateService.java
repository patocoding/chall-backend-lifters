package org.elections.services;
import org.elections.dtos.CandidateReportDTO;
import org.elections.models.Candidate;
import org.elections.models.Position;
import org.elections.repositories.*;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateService {
    private final CandidateRepository candidateRepository;
    private final VoteRepository voteRepository;
    private final PositionRepository positionRepository;
    private static final Logger logger = LoggerFactory.getLogger(CandidateService.class);

    public CandidateService(CandidateRepository candidateRepository, VoteRepository voteRepository, PositionRepository positionRepository) {
        this.candidateRepository = candidateRepository;
        this.voteRepository = voteRepository;
        this.positionRepository = positionRepository;
    }

    public Candidate createCandidate(String name, Position position) {
        // ve se o cargo existe no banco
        logger.info("Creating candidate for position: ID = {}, Name = {}", position.getId(), position.getName());
        Position existingPosition = positionRepository.findById(position.getId())
                .orElseThrow(() -> new RuntimeException("Position not found"));


        Candidate candidate = new Candidate();
        candidate.setName(name);
        candidate.setPosition(existingPosition);

        return candidateRepository.save(candidate);
    }

    // todos candidatos
    public List<Candidate> getAll() {
        return candidateRepository.findAll();
    }

    public Candidate findById(Long id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));
    }

    public Candidate updateCandidate(Long id, Candidate updatedCandidate) {
        Candidate existingCandidate = findById(id);
        existingCandidate.setName(updatedCandidate.getName());

        if (updatedCandidate.getPosition() != null) {
            Position newPosition = positionRepository.findById(updatedCandidate.getPosition().getId())
                    .orElseThrow(() -> new RuntimeException("Position not found"));
            existingCandidate.setPosition(newPosition);
        }

        return candidateRepository.save(existingCandidate);
    }


    public void deleteCandidate(Long id) {
        Candidate candidate = findById(id);
        candidateRepository.delete(candidate);
    }
}
