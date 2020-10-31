package com.example.demo.service;

import com.example.demo.model.House;

public interface HouseService {
    Iterable<House> findAll();

    House findById(Long id);

    void save(House house);

    void remove(Long id);
}
