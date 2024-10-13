package org.elections.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "voters")
public class Voter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = true)
    private String document;
    @OneToMany(mappedBy = "voter")
    @JsonManagedReference
    @JsonIgnore
    private Set<Vote> votes = new HashSet<>();

    public Voter() {}


    public void addVote(Vote vote) {
        this.votes.add(vote);
        vote.setVoter(this);
    }
}