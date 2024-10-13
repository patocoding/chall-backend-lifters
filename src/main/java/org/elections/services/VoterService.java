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
            throw new RuntimeException("já existe um eleitor com esse documento"); // verificação para não criar com o mesmo doc
        }

        Voter voter = new Voter();
        voter.setName(name);
        voter.setDocument(document);
        return voterRepository.save(voter); // criando eleitor
    }

    public List<Voter> findAll() {
        return voterRepository.findAll(); // retorna todos os eleitores
    }

    public Voter findById(Long id) {
        return voterRepository.findById(id) // procura o eleitor no banco de dados a partir do id
                .orElseThrow(() -> new RuntimeException("eleitor nao encontrado"));
    }

    public Voter updateVoter(Long id, String name, String document) {
        Voter voter = findById(id); // procura o eleitor no banco de dados
        voter.setName(name); // seta o nome
        voter.setDocument(document); // seta o documento ( podia ser cpf )
        return voterRepository.save(voter); // faz o update
    }

    public void deleteVoter(Long id) {
        Voter voter = findById(id); // procura o eleitor no banco de dados
        if (!voter.getVotes().isEmpty()) { // se tiver votos
            throw new RuntimeException("não é possivel deletar eleitor com votos");
        }
        voterRepository.delete(voter); // se não tiver votos, deleta
    }
}
