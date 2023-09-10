package com.example.demo.controller;

import java.util.Optional;

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

import com.example.demo.entity.Country;
import com.example.demo.services.ICountryService;

@RestController
@RequestMapping("/api/country")
public class CountryController {

    @Autowired
    private ICountryService countryService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(countryService.findAll());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/{countryId}")
    public ResponseEntity<?> findById(@PathVariable("countryId") Integer countryId) {
        try {
            Optional<Country> oCountry = countryService.findById(countryId);
            if (oCountry.isPresent())
                return ResponseEntity.ok(oCountry.get());
            return new ResponseEntity<>("Country is not exist", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> insert(@RequestBody Country country) {
        try {
            Optional<Country> oCountry = countryService.insert(country);
            if (oCountry.isPresent())
                return ResponseEntity.ok(oCountry.get());
            return new ResponseEntity<>("Insert error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping("/{countryId}")
    public ResponseEntity<?> update(@PathVariable("countryId") Integer countryId, @RequestBody Country country) {
        try {
            Optional<Country> oCountry = countryService.update(countryId, country);
            if (oCountry.isPresent())
                return ResponseEntity.ok(oCountry.get());
            return new ResponseEntity<>("Update error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.BAD_GATEWAY);
        }
    }
}
