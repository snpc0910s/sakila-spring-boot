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

import com.example.demo.entity.Staff;
import com.example.demo.services.IStaffService;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    private IStaffService staffService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(staffService.findAll());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/{staffId}")
    public ResponseEntity<?> findById(@PathVariable("staffId") Integer staffId) {
        try {
            Optional<Staff> oStaff = staffService.findById(staffId);
            if (oStaff.isPresent())
                return ResponseEntity.ok(oStaff.get());
            return new ResponseEntity<>("Staff is not exist", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> insert(@RequestBody Staff staff) {
        try {
            Optional<Staff> oStaff = staffService.insert(staff);
            if (oStaff.isPresent())
                return ResponseEntity.ok(oStaff.get());
            return new ResponseEntity<>("Insert error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping("/{staffId}")
    public ResponseEntity<?> update(@PathVariable("staffId") Integer staffId, @RequestBody Staff staff) {
        try {
            Optional<Staff> oStaff = staffService.update(staffId, staff);
            if (oStaff.isPresent())
                return ResponseEntity.ok(oStaff.get());
            return new ResponseEntity<>("Update error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.BAD_GATEWAY);
        }
    }
}
