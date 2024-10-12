package org.elections.dtos;

import lombok.Data;

@Data
public class CandidateReportDTO {
    private Long positionId;
    private String positionName;
    private long voteCount;
    private Long candidateId;
    private String candidateName;

    public CandidateReportDTO(Long positionId, String positionName, long voteCount, Long candidateId, String candidateName) {
        this.positionId = positionId;
        this.positionName = positionName;
        this.voteCount = voteCount;
        this.candidateId = candidateId;
        this.candidateName = candidateName;
    }
}
