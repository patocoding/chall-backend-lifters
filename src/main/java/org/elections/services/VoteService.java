package org.elections.services;

import org.elections.models.Vote;
import org.elections.repositories.CandidateRepository;
import org.elections.repositories.VoteRepository;
import org.elections.repositories.VoterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final VoterRepository voterRepository;
    private final CandidateRepository candidateRepository;

    public VoteService(VoteRepository voteRepository, VoterRepository voterRepository, CandidateRepository candidateRepository) {
        this.voteRepository = voteRepository;
        this.voterRepository = voterRepository;
        this.candidateRepository = candidateRepository;
    }

    public Vote vote(Long voterId, Long candidateId) {
        if (voteRepository.existsByVoterIdAndCandidatePositionId(voterId, candidateId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Eleitor já votou para essa posição");
        } // eleitor nao vota mais de uma vez no mesmo cargo

        var voter = voterRepository.findById(voterId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Eleitor não encontrado"));
        var candidate = candidateRepository.findById(candidateId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Candidato não encontrado"));

        Vote vote = new Vote();
        vote.setVoter(voter);
        vote.setCandidate(candidate);

        return voteRepository.save(vote);
    }
}
