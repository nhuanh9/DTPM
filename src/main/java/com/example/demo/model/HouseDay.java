package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class HouseDay {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    private String status;

    @ManyToOne
    @JoinColumn(name = "id_house")
    private House house;

    public HouseDay(Date date1, String s, House house) {
        this.date = date1;
        this.status = s;
        this.house = house;
    }

    public HouseDay() {
    }
}
