package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Staff;

public interface IStaffService {
    public List<Staff> findAll();

    public Optional<Staff> findById(Integer id);

    public boolean exist(Integer id);

    public Optional<Staff> insert(Staff saveStaff);

    public Optional<Staff> update(Integer staffId, Staff saveStaff);
}

