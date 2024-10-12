package org.elections.models;
import java.util.Set;
import java.util.HashSet;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @ManyToOne
    private Position position;

    @Setter
    @Getter
    @OneToMany(mappedBy = "candidate")
    private Set<Vote> votes = new HashSet<>();

    public Candidate() {}

    public Candidate(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
