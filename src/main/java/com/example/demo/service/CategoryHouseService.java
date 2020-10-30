package com.example.demo.service;

import com.example.demo.model.CategoryHouse;

public interface CategoryHouseService {
    Iterable<CategoryHouse> findAll();

    CategoryHouse findById(Long id);

    void save(CategoryHouse categoryHouse);

    void remove(Long id);
}
