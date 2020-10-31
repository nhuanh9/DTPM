package com.example.demo.service;

import com.example.demo.model.HouseDay;

import java.util.Date;

public interface HouseDayService {
    Iterable<HouseDay> findAll();

    HouseDay findById(Date date);

    void save(HouseDay houseDay);

    void remove(Date date);
}
