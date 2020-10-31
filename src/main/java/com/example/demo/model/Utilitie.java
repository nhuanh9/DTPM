package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Utilitie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUtilitie;

    private String name;

    private String description;

    @ManyToMany
    Set<House> houses;
}
