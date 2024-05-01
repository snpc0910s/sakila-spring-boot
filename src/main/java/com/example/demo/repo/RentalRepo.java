package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.demo.repo.custom.RentalRepoCustom;
import com.example.demo.entity.Rental;

@Repository
public interface RentalRepo extends JpaRepository<Rental,Integer> , RentalRepoCustom{
}
