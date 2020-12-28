package com.example.demo.controller;

import com.example.demo.model.House;
import com.example.demo.model.Image;
import com.example.demo.service.impl.HouseServiceImpl;
import com.example.demo.service.impl.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@CrossOrigin("*")
@RestController
public class HouseController {
    @Autowired
    private HouseServiceImpl houseService;

    @Autowired
    private ImageServiceImpl imageService;

    @RequestMapping(value = "/api/houses", method = RequestMethod.GET)
    public ResponseEntity<Iterable<House>> listAllHouse() {
        Iterable<House> houses = houseService.findAll();
        if (houses == null) {
            return new ResponseEntity<Iterable<House>>(HttpStatus.NO_CONTENT);
        }
        //push
        return new ResponseEntity<Iterable<House>>(houses, HttpStatus.OK);
    }


    @RequestMapping(value = "/api/houses/{id}", method = RequestMethod.GET)
    public ResponseEntity<House> getHouse(@PathVariable("id") Long id) {
        House house = houseService.findById(id);
        if (house == null) {
            return new ResponseEntity<House>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<House>(house, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/houses", method = RequestMethod.POST)
    public ResponseEntity<Void> createHouse(@RequestBody House house, UriComponentsBuilder ucBuilder) {
        houseService.save(house);
        for (Image image : house.getImageList()) {
            image.setHouse(house);
            imageService.save(image);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/houses/{id}").buildAndExpand(house.getIdHouse()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/houses/{id}", method = RequestMethod.PUT)
    public ResponseEntity<House> updateHouse(@PathVariable("id") Long id, @RequestBody House house) {
        House house1 = houseService.findById(id);
        List<Image> imageListFound = house1.getImageList(); // mang anh goc
        if (house1 == null) {
            return new ResponseEntity<House>(HttpStatus.NOT_FOUND);
        }
        house1.setName(house.getName());
        house1.setAddress(house.getAddress());
        house1.setDescription(house.getDescription());
        house1.setRoomNumber(house.getRoomNumber());
        house1.setPrice(house.getPrice());
        house1.setAccount(house.getAccount());
        house1.setCategoryHouse(house.getCategoryHouse());
        house1.setUtilities(house.getUtilities());
        List<Image> imageList = house.getImageList(); // mang anh lay ve
        for (Image image : imageList) {
            if (image.getIdImage() == null) {
                image.setHouse(house1);
                imageService.save(image);
            } else {
                imageService.remove(image.getIdImage());
            }
        }
        houseService.save(house1);
        return new ResponseEntity<House>(HttpStatus.OK);
    }


    @RequestMapping(value = "/api/houses/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<House> deleteHouse(@PathVariable("id") Long id) {
        House house = houseService.findById(id);
        if (house == null) {
            return new ResponseEntity<House>(HttpStatus.NOT_FOUND);
        }
        houseService.remove(id);
        return new ResponseEntity<House>(HttpStatus.NO_CONTENT);
    }
}
