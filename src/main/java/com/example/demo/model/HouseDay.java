package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class HouseDay {
    @Id
    private Date date;

    private String status;

    @ManyToOne
    @JoinColumn(name = "id_house")
    private House house;
}
