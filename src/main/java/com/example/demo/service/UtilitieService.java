package com.example.demo.service;

import com.example.demo.model.CategoryHouse;
import com.example.demo.model.Utilitie;

public interface UtilitieService {
    Iterable<Utilitie> findAll();

    Utilitie findById(Long id);

    void save(Utilitie utilitie);

    void remove(Long id);
}
