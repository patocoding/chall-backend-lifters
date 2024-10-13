package org.elections.controllers;

import org.elections.dtos.ResultDTO;
import org.elections.models.Voter;
import org.elections.services.VoterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.List;

@RestController
@RequestMapping("/api/voters")
public class VoterController {

    private final VoterService voterService;

    public VoterController(VoterService voterService) {
        this.voterService = voterService;
    }

    @PostMapping
    public ResponseEntity<ResultDTO<Voter>> createVoter(@RequestBody Voter voter) {
        Voter createdVoter = voterService.createVoter(voter.getName(), voter.getDocument());
        ResultDTO<Voter> resultDTO = new ResultDTO<Voter>("Eleitor criado com sucesso",createdVoter);
        return ResponseEntity.ok(resultDTO);
    }

    @GetMapping
    public ResponseEntity<List<Voter>> getAllVoters() {
        List<Voter> voters = voterService.findAll();
        return ResponseEntity.ok(voters);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Voter> getVoterById(@PathVariable Long id) {
        Voter voter = voterService.findById(id);
        return ResponseEntity.ok(voter);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Voter> updateVoter(@PathVariable Long id, @RequestBody Voter voter) {
        Voter updatedVoter = voterService.updateVoter(id, voter.getName(), voter.getDocument());
        return ResponseEntity.ok(updatedVoter);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoter(@PathVariable Long id) {
        voterService.deleteVoter(id);
        return ResponseEntity.noContent().build();
    }
}
