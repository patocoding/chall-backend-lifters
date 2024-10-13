package org.elections.models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Entity
@Table(name = "votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private Candidate candidate;

    @ManyToOne
    @JsonBackReference
    private Voter voter;

    public Vote() {}


    public void setId(Long id) {
        this.id = id;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}