package com.example.demo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idService;

    private String name;

    private Double price;
    
    private String description;
}
