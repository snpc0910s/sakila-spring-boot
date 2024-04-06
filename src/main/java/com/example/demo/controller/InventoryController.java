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

import com.example.demo.entity.Inventory;
import com.example.demo.services.IInventoryService;

@RestController
@RequestMapping(BaseConst.BASE_API + "/inventory")
public class InventoryController {

    @Autowired
    private IInventoryService inventoryService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(inventoryService.findAll());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/{inventoryId}")
    public ResponseEntity<?> findById(@PathVariable("inventoryId") Integer inventoryId) {
        try {
            Optional<Inventory> oInventory = inventoryService.findById(inventoryId);
            if (oInventory.isPresent())
                return ResponseEntity.ok(oInventory.get());
            return new ResponseEntity<>("Inventory is not exist", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> insert(@RequestBody Inventory inventory) {
        try {
            Optional<Inventory> oInventory = inventoryService.insert(inventory);
            if (oInventory.isPresent())
                return ResponseEntity.ok(oInventory.get());
            return new ResponseEntity<>("Insert error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping("/{inventoryId}")
    public ResponseEntity<?> update(@PathVariable("inventoryId") Integer inventoryId, @RequestBody Inventory inventory) {
        try {
            Optional<Inventory> oInventory = inventoryService.update(inventoryId, inventory);
            if (oInventory.isPresent())
                return ResponseEntity.ok(oInventory.get());
            return new ResponseEntity<>("Update error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.BAD_GATEWAY);
        }
    }
}
