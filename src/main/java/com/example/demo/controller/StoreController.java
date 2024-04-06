package com.example.demo.controller;

import java.util.Optional;

import com.example.demo.base.BaseConst;
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

import com.example.demo.entity.Store;
import com.example.demo.services.IStoreService;

@RestController
@RequestMapping(BaseConst.BASE_API + "/store")
public class StoreController {

    @Autowired
    private IStoreService storeService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(storeService.findAll());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<?> findById(@PathVariable("storeId") Integer storeId) {
        try {
            Optional<Store> oStore = storeService.findById(storeId);
            if (oStore.isPresent())
                return ResponseEntity.ok(oStore.get());
            return new ResponseEntity<>("Store is not exist", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> insert(@RequestBody Store store) {
        try {
            Optional<Store> oStore = storeService.insert(store);
            if (oStore.isPresent())
                return ResponseEntity.ok(oStore.get());
            return new ResponseEntity<>("Insert error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping("/{storeId}")
    public ResponseEntity<?> update(@PathVariable("storeId") Integer storeId, @RequestBody Store store) {
        try {
            Optional<Store> oStore = storeService.update(storeId, store);
            if (oStore.isPresent())
                return ResponseEntity.ok(oStore.get());
            return new ResponseEntity<>("Update error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.BAD_GATEWAY);
        }
    }
}
