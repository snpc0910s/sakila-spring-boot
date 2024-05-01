package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.dto.ActorDTO;
import com.example.demo.entity.Actor;

public interface IActorService {
    public List<Actor> findAll();

    public Optional<Actor> findById(Integer id);

    public boolean exist(Integer id);

    public Optional<Actor> insert(Actor saveActor);

    public Optional<Actor> update(Integer actorId, Actor saveActor);

}

