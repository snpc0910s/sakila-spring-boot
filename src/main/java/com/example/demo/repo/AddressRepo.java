package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.demo.repo.custom.AddressRepoCustom;
import com.example.demo.entity.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address,Integer> , AddressRepoCustom{
}
