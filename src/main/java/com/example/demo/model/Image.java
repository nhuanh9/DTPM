package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idImage;

    private String link;


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_house")
    private House house;
}
