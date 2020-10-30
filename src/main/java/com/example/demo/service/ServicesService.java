package com.example.demo.service;

import com.example.demo.model.Services;

public interface ServicesService {
    Iterable<Services> findAll();

    Services findById(Long id);

    void save(Services service);

    void remove(Long id);
}
