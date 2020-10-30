package com.example.demo.service.impl;

import com.example.demo.model.Utilitie;
import com.example.demo.repository.UtilitieRepository;
import com.example.demo.service.UtilitieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilitieServiceImpl implements UtilitieService {
    @Autowired
    private UtilitieRepository utilitieRepository;

    @Override
    public Iterable<Utilitie> findAll() {
        return utilitieRepository.findAll();
    }

    @Override
    public Utilitie findById(Long id) {
        return utilitieRepository.findById(id).get();
    }

    @Override
    public void save(Utilitie utilitie) {
        utilitieRepository.save(utilitie);
    }

    @Override
    public void remove(Long id) {
        utilitieRepository.deleteById(id);
    }
}
