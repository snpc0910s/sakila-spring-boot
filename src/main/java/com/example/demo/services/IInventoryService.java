package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Inventory;

public interface IInventoryService {
    public List<Inventory> findAll();

    public Optional<Inventory> findById(Integer id);

    public boolean exist(Integer id);

    public Optional<Inventory> insert(Inventory saveInventory);

    public Optional<Inventory> update(Integer inventoryId, Inventory saveInventory);
}

