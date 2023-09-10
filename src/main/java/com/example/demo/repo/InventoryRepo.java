package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Inventory;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory,Integer>{
}
