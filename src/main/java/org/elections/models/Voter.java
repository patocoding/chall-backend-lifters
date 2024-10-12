package org.elections.models;


import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Voter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "voter")
    private Set<Vote> votes = new HashSet<>();

}