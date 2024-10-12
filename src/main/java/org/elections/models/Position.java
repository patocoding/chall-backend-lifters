package org.elections.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "positions")
public class Position {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private String name;

    @OneToMany(mappedBy = "position")
    private Set<Candidate> candidates = new HashSet<>();

    public Position () {}
}