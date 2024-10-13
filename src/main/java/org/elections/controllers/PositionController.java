package org.elections.controllers;

import org.elections.dtos.ResultDTO;
import org.elections.models.Position;
import org.elections.repositories.PositionRepository;
import org.elections.services.PositionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/positions")
public class PositionController {

    private final PositionService positionService;

    public PositionController(PositionService positionService, PositionRepository positionRepository, DataSource dataSource) {
        this.positionService = positionService;
    }


    @PostMapping
    public ResponseEntity<ResultDTO<Position>> createPosition(@RequestBody Position position) {
        Position createdPosition = positionService.createPosition(position.getName());
        ResultDTO<Position> resultDTO = new ResultDTO<>("Cargo criado com sucesso", createdPosition);
        return ResponseEntity.ok(resultDTO);
    }

    @GetMapping
    public ResponseEntity<List<Position>> getAllPositions() {
        List<Position> positions = positionService.findAll();
        return ResponseEntity.ok(positions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Position> getPositionById(@PathVariable Long id) {
        Position position = positionService.findById(id);
        return ResponseEntity.ok(position);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Position> updatePosition(@PathVariable Long id, @RequestBody Position position) {
        Position updatedPosition = positionService.updatePosition(id, position.getName());
        return ResponseEntity.ok(updatedPosition);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePosition(@PathVariable Long id) {
        positionService.deletePosition(id);
        return ResponseEntity.noContent().build();
    }
}