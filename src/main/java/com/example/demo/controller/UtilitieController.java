package com.example.demo.controller;

import com.example.demo.model.Utilitie;
import com.example.demo.service.impl.UtilitieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin("*")
@RestController
public class UtilitieController {
    @Autowired
    private UtilitieServiceImpl utilitieService;

    @RequestMapping(value = "/api/utilities", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Utilitie>> listAllUtilitie() {
        Iterable<Utilitie> utilitie = utilitieService.findAll();
        if (utilitie == null) {
            return new ResponseEntity<Iterable<Utilitie>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Iterable<Utilitie>>(utilitie, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/utilities/{id}", method = RequestMethod.GET)
    public ResponseEntity<Utilitie> getUtilitie(@PathVariable("id") Long id) {
        Utilitie utilitie = utilitieService.findById(id);
        if (utilitie == null) {
            return new ResponseEntity<Utilitie>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Utilitie>(utilitie, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/utilities", method = RequestMethod.POST)
    public ResponseEntity<Void> addUtilitie(@RequestBody Utilitie utilitie, UriComponentsBuilder ucBuilder) {
        utilitieService.save(utilitie);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/utilities/{id}").buildAndExpand(utilitie.getIdUtilitie()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/utilities/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Utilitie> updateUtilitie(@PathVariable("id") Long id, @RequestBody Utilitie utilitie) {
        Utilitie utilitie1 = utilitieService.findById(id);

        if (utilitie1 == null) {
            return new ResponseEntity<Utilitie>(HttpStatus.NOT_FOUND);
        }
        utilitie1.setName(utilitie.getName());
        utilitie1.setDescription(utilitie.getDescription());

        utilitieService.save(utilitie1);
        return new ResponseEntity<Utilitie>(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/utilities/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Utilitie> deleteutilitie(@PathVariable("id") Long id) {
        Utilitie utilitie = utilitieService.findById(id);
        if (utilitie == null) {
            return new ResponseEntity<Utilitie>(HttpStatus.NOT_FOUND);
        }
        utilitieService.remove(id);
        return new ResponseEntity<Utilitie>(HttpStatus.OK);
    }
}
