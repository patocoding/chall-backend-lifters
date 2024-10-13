package org.elections.dtos;


import lombok.Getter;

@Getter
public class CandidateReportDTO {
    private Long idCargo;
    private String nomeCargo;
    private Long votos;
    private Long idCandidatoVencedor;
    private String nomeCandidatoVencedor;

    public CandidateReportDTO(Long idCargo, String nomeCargo, Long votos, Long idCandidatoVencedor, String nomeCandidatoVencedor) {
        this.idCargo = idCargo;
        this.nomeCargo = nomeCargo;
        this.votos = votos;
        this.idCandidatoVencedor = idCandidatoVencedor;
        this.nomeCandidatoVencedor = nomeCandidatoVencedor;
    }

}