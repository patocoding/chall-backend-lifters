package org.elections.models;
import java.util.Set;
import java.util.HashSet;
import jakarta.persistence.*;

@Entity
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    private Position position;

    @OneToMany(mappedBy = "candidate")
    private Set<Vote> votes = new HashSet<>();


    public Candidate(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }
}
