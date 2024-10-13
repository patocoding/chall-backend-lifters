package org.elections.services;

import org.elections.models.Voter;
import org.elections.repositories.VoterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoterService {

    private final VoterRepository voterRepository;

    public VoterService(VoterRepository voterRepository) {
        this.voterRepository = voterRepository;
    }

    public Voter createVoter(String name, String document) {
        if (voterRepository.existsByDocument(document)) {
            throw new RuntimeException("Voter with this document already exists");
        }

        Voter voter = new Voter();
        voter.setName(name);
        voter.setDocument(document);
        return voterRepository.save(voter);
    }

    public List<Voter> findAll() {
        return voterRepository.findAll();
    }

    public Voter findById(Long id) {
        return voterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Voter not found"));
    }

    public Voter updateVoter(Long id, String name, String document) {
        Voter voter = findById(id);
        voter.setName(name);
        voter.setDocument(document);
        return voterRepository.save(voter);
    }

    public void deleteVoter(Long id) {
        Voter voter = findById(id);
        if (!voter.getVotes().isEmpty()) {
            throw new RuntimeException("Cannot delete voter with votes");
        }
        voterRepository.delete(voter);
    }
}
