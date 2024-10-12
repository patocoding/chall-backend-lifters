package org.elections.services;
import org.elections.dtos.CandidateReportDTO;
import org.elections.models.Candidate;
import org.elections.repositories.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateService {
    private final CandidateRepository candidateRepository;
    private final VoteRepository voteRepository;

    public CandidateService(CandidateRepository candidateRepository, VoteRepository voteRepository) {
        this.candidateRepository = candidateRepository;
        this.voteRepository = voteRepository;
    }

    public List<CandidateReportDTO> getCandidatesReport() {
        List<Candidate> allCandidates = candidateRepository.findAll();

        return allCandidates.stream().map(candidate -> {
            long votes = voteRepository.countByCandidateId(candidate.getId());
            return new CandidateReportDTO(candidate.getPosition().getId(),
                    candidate.getPosition().getName(),
                    votes,
                    candidate.getId(),
                    candidate.getName());
        }).collect(Collectors.toList());
    }
}
