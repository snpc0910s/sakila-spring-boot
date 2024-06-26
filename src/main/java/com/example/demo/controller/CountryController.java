package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.base.BaseConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Country;
import com.example.demo.services.ICountryService;

@RestController
@RequestMapping(BaseConst.BASE_API + "/country")
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

//    @PostMapping("/search")
//    public ResponseEntity<?> findDynamicWhereCondition(@RequestBody Country country) {
//        try {
//            return ResponseEntity.ok(countryService.getDynamicWhereCondition(country, PageRequest.of(0, 100)));
//        } catch (Exception e) {
//            return new ResponseEntity<>("Server error", HttpStatus.BAD_GATEWAY);
//        }
//    }

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
