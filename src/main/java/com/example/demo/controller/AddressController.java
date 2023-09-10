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

import com.example.demo.entity.Address;
import com.example.demo.services.IAddressService;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private IAddressService addressService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(addressService.findAll());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<?> findById(@PathVariable("addressId") Integer addressId) {
        try {
            Optional<Address> oAddress = addressService.findById(addressId);
            if (oAddress.isPresent())
                return ResponseEntity.ok(oAddress.get());
            return new ResponseEntity<>("Address is not exist", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> insert(@RequestBody Address address) {
        try {
            Optional<Address> oAddress = addressService.insert(address);
            if (oAddress.isPresent())
                return ResponseEntity.ok(oAddress.get());
            return new ResponseEntity<>("Insert error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<?> update(@PathVariable("addressId") Integer addressId, @RequestBody Address address) {
        try {
            Optional<Address> oAddress = addressService.update(addressId, address);
            if (oAddress.isPresent())
                return ResponseEntity.ok(oAddress.get());
            return new ResponseEntity<>("Update error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.BAD_GATEWAY);
        }
    }
}
