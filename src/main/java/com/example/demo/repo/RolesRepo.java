package com.example.demo.repo;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Roles;

@Repository
public interface RolesRepo extends JpaRepository<Roles,Integer> {

    @Query(value = "select r from Roles r where r.name = :name")
    Optional<Roles> findByName(String name);

    @Query(value="select r from Roles r where r.name in :names")
    public List<Roles> findByNameList(@Param("names") Collection<String> names);
}
