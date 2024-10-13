package org.elections.controllers;
import org.elections.dtos.ResultDTO;
import org.elections.models.Vote;
import org.elections.services.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/api/voters")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/{id}/vote")
    public ResponseEntity<ResultDTO<Vote>> castVote(@PathVariable Long id, @RequestBody Map<String, Long> requestBody) {
        Long candidateId = requestBody.get("candidateId");
        if (candidateId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Informe o ID do candidato");
        }

        try {
            Vote vote = voteService.vote(id, candidateId);
            ResultDTO<Vote> resultDTO = new ResultDTO<>("Voto computado com sucesso", vote);
            return ResponseEntity.ok(resultDTO);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}