package com.example.demo.controller;

import com.example.demo.model.House;
import com.example.demo.model.Image;
import com.example.demo.service.impl.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin("*")
@RestController
public class ImageController {
    @Autowired
    private ImageServiceImpl imageService;

    @RequestMapping(value = "/api/images", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Image>> listAllImage() {
        Iterable<Image> images = imageService.findAll();
        if (images == null) {
            return new ResponseEntity<Iterable<Image>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Iterable<Image>>(images, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/images/{id}", method = RequestMethod.GET)
    public ResponseEntity<Image> getImage(@PathVariable("id") Long id) {
        Image image = imageService.findById(id);
        if (image == null) {
            return new ResponseEntity<Image>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Image>(image, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/images", method = RequestMethod.POST)
    public ResponseEntity<Void> createImage(@RequestBody Image image, UriComponentsBuilder ucBuilder) {
        imageService.save(image);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/images/{id}").buildAndExpand(image.getIdImage()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/images/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Image> updateImage(@PathVariable("id") Long id, @RequestBody Image image) {
        Image images = imageService.findById(id);

        if (images == null) {
            return new ResponseEntity<Image>(HttpStatus.NOT_FOUND);
        }
        images.setLink(image.getLink());
        return new ResponseEntity<Image>(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/images/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Image> deleteImage(@PathVariable("id") Long id) {
        Image image = imageService.findById(id);
        if (image == null) {
            return new ResponseEntity<Image>(HttpStatus.NOT_FOUND);
        }
        imageService.remove(id);
        return new ResponseEntity<Image>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/api/findAllByHouse")
    public ResponseEntity<Iterable<Image>> findAllByHouse(@RequestParam("house") House id) {
        Iterable<Image> images = imageService.findAllByHouse(id);
        if (images == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(images, HttpStatus.OK);
    }
}
