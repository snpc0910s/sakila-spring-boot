package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Rental;

@Repository
public interface RentalRepo extends JpaRepository<Rental,Integer>{
}
