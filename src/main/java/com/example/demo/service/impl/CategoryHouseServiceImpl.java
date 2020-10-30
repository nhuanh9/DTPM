package com.example.demo.service.impl;

import com.example.demo.model.CategoryHouse;
import com.example.demo.repository.CategoryHouseRepository;
import com.example.demo.service.CategoryHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryHouseServiceImpl implements CategoryHouseService {
    @Autowired
    private CategoryHouseRepository categoryHouseRepository;

    @Override
    public Iterable<CategoryHouse> findAll() {
        return categoryHouseRepository.findAll();
    }

    @Override
    public CategoryHouse findById(Long id) {
        return categoryHouseRepository.findById(id).get();
    }

    @Override
    public void save(CategoryHouse categoryHouse) {
        categoryHouseRepository.save(categoryHouse);
    }

    @Override
    public void remove(Long id) {
        categoryHouseRepository.deleteById(id);
    }
}
