package org.elections.controllers;

import org.elections.dtos.CandidateReportDTO;
import org.elections.dtos.ResultDTO;
import org.elections.models.Candidate;
import org.elections.models.Vote;
import org.elections.services.CandidateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/api/candidatos")
public class CandidateController {
    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }
    @PostMapping
    public ResponseEntity<Candidate> createCandidate(@RequestBody Candidate candidate) {
        Candidate createdCandidate = candidateService.createCandidate(candidate.getName(), candidate.getPosition());
        return ResponseEntity.ok(createdCandidate);
    }

    @GetMapping
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        List<Candidate> candidates = candidateService.getAll();
        return ResponseEntity.ok(candidates);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResultDTO<Candidate>> getCandidateById(@PathVariable Long id) {

        try {
            Candidate candidate = candidateService.findById(id);
            ResultDTO<Candidate> resultDTO = new ResultDTO<>("Candidato encontrado com sucesso", candidate);
            return ResponseEntity.ok(resultDTO);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidate> updateCandidate(@PathVariable Long id, @RequestBody Candidate candidate) {
        try {
            Candidate updatedCandidate = candidateService.updateCandidate(id, candidate);
            return ResponseEntity.ok(updatedCandidate);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/report")
    public ResponseEntity<List<CandidateReportDTO>> getCandidatesReport() {

        try {
            List<CandidateReportDTO> report = candidateService.getCandidatesReport();
            return ResponseEntity.ok(report);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
