package com.example.demo.controller;

import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.City;
import com.example.demo.services.ICityService;

@RestController
@RequestMapping("/api/city")
public class CityController {

    @Autowired
    private ICityService cityService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(cityService.findAll());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<?> findById(@PathVariable("cityId") Integer cityId) {
        try {
            Optional<City> oCity = cityService.findById(cityId);
            if (oCity.isPresent())
                return ResponseEntity.ok(oCity.get());
            return new ResponseEntity<>("City is not exist", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> insert(@RequestBody City city) {
        try {
            Optional<City> oCity = cityService.insert(city);
            if (oCity.isPresent())
                return ResponseEntity.ok(oCity.get());
            return new ResponseEntity<>("Insert error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping("/{cityId}")
    public ResponseEntity<?> update(@PathVariable("cityId") Integer cityId, @RequestBody City city) {
        try {
            Optional<City> oCity = cityService.update(cityId, city);
            if (oCity.isPresent())
                return ResponseEntity.ok(oCity.get());
            return new ResponseEntity<>("Update error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.BAD_GATEWAY);
        }
    }
}
