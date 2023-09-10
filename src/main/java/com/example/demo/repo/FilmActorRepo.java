package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.FilmActor;
import com.example.demo.entity.FilmActor.FilmActorId;

@Repository
public interface FilmActorRepo extends JpaRepository<FilmActor,FilmActorId>{
}
