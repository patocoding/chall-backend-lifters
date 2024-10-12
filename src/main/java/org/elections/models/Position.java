package org.elections.models;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "position")
    private Set<Candidate> candidates = new HashSet<>();

}