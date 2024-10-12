package org.elections.services;

import org.elections.models.Position;
import org.elections.repositories.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PositionService {

    private final PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public Position createPosition(String name) {

        if (positionRepository.existsByName(name)) {
            throw new IllegalStateException("Cargo já existente: " + name);
        }

        Position position = new Position();
        position.setName(name);
        return positionRepository.save(position);
    }

    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    public Position findById(Long id) {
        return positionRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Cargo não encontrado"));
    }

    public void deletePosition(Long id) {
        positionRepository.deleteById(id);
    }
}