package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Actor;

@Repository
public interface ActorRepo extends JpaRepository<Actor,Integer>{
}
