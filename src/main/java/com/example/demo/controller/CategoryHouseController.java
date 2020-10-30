package com.example.demo.controller;

import com.example.demo.model.CategoryHouse;
import com.example.demo.service.impl.CategoryHouseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin("*")
@RestController
public class CategoryHouseController {
    @Autowired
    private CategoryHouseServiceImpl categoryHouseService;

    @RequestMapping(value = "/api/categoryHouses", method = RequestMethod.GET)
    public ResponseEntity<Iterable<CategoryHouse>> listAllCategoryHouse() {
        Iterable<CategoryHouse> categoryHouse = categoryHouseService.findAll();
        if (categoryHouse == null) {
            return new ResponseEntity<Iterable<CategoryHouse>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Iterable<CategoryHouse>>(categoryHouse, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/categoryHouses/{id}", method = RequestMethod.GET)
    public ResponseEntity<CategoryHouse> getCategoryHouse(@PathVariable("id") Long id) {
        CategoryHouse categoryHouse = categoryHouseService.findById(id);
        if (categoryHouse == null) {
            return new ResponseEntity<CategoryHouse>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CategoryHouse>(categoryHouse, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/categoryHouses", method = RequestMethod.POST)
    public ResponseEntity<Void> createCategoryHouse(@RequestBody CategoryHouse categoryHouse, UriComponentsBuilder ucBuilder) {
        categoryHouseService.save(categoryHouse);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/categoryHouses/{id}").buildAndExpand(categoryHouse.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/categoryHouses/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CategoryHouse> updateCategoryHouse(@PathVariable("id") Long id, @RequestBody CategoryHouse categoryHouse) {
        CategoryHouse categoryHouse1 = categoryHouseService.findById(id);

        if (categoryHouse1 == null) {
            return new ResponseEntity<CategoryHouse>(HttpStatus.NOT_FOUND);
        }
        categoryHouse1.setName(categoryHouse.getName());
        categoryHouse1.setDescription(categoryHouse.getDescription());

        categoryHouseService.save(categoryHouse1);
        return new ResponseEntity<CategoryHouse>(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/categoryHouses/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CategoryHouse> deleteCategoryHouse(@PathVariable("id") Long id) {
        CategoryHouse categoryHouse = categoryHouseService.findById(id);
        if (categoryHouse == null) {
            return new ResponseEntity<CategoryHouse>(HttpStatus.NOT_FOUND);
        }
        categoryHouseService.remove(id);
        return new ResponseEntity<CategoryHouse>(HttpStatus.OK);
    }

}