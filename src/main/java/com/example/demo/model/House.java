package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idHouse;

    private String name;

    private String address;

    private String description;

    private String roomNumber;

    private Double price;

    private String Account;

    @ManyToOne
    @JoinColumn(name = "id_categoryhouse")
    private CategoryHouse categoryHouse;

    @ManyToMany
    Set<Utilitie> utilities;

    @ManyToMany
    Set<Services> services;

    @Column(columnDefinition = "TEXT")
    private String imageUrls;

    @JsonManagedReference
    @OneToMany(targetEntity = Image.class, mappedBy = "house", cascade = CascadeType.REMOVE)
    private List<Image> imageList;
}
