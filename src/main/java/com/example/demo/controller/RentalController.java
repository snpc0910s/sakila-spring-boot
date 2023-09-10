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

import com.example.demo.entity.Rental;
import com.example.demo.services.IRentalService;

@RestController
@RequestMapping("/api/rental")
public class RentalController {

    @Autowired
    private IRentalService rentalService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(rentalService.findAll());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/{rentalId}")
    public ResponseEntity<?> findById(@PathVariable("rentalId") Integer rentalId) {
        try {
            Optional<Rental> oRental = rentalService.findById(rentalId);
            if (oRental.isPresent())
                return ResponseEntity.ok(oRental.get());
            return new ResponseEntity<>("Rental is not exist", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> insert(@RequestBody Rental rental) {
        try {
            Optional<Rental> oRental = rentalService.insert(rental);
            if (oRental.isPresent())
                return ResponseEntity.ok(oRental.get());
            return new ResponseEntity<>("Insert error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping("/{rentalId}")
    public ResponseEntity<?> update(@PathVariable("rentalId") Integer rentalId, @RequestBody Rental rental) {
        try {
            Optional<Rental> oRental = rentalService.update(rentalId, rental);
            if (oRental.isPresent())
                return ResponseEntity.ok(oRental.get());
            return new ResponseEntity<>("Update error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.BAD_GATEWAY);
        }
    }
}
