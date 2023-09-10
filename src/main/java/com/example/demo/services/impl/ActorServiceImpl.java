package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Actor;
import com.example.demo.repo.ActorRepo;
import com.example.demo.services.IActorService;

@Service
public class ActorServiceImpl implements IActorService {

    @Autowired
    private ActorRepo actorRepo;

    @Transactional(readOnly = true)
    @Override
    public List<Actor> findAll() {
        return actorRepo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Actor> findById(Integer id) {
        return actorRepo.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean exist(Integer id) {
        return actorRepo.existsById(id);
    }

    @Transactional(readOnly = false)
    @Override
    public Optional<Actor> insert(Actor saveActor) {
        Actor actor = new Actor();
        actor.setActorId(null);
        actor.setFirstName(saveActor.getFirstName());
        actor.setLastName(saveActor.getLastName());
        actor.setLastUpdate(saveActor.getLastUpdate());
        Actor o = actorRepo.save(actor);
        return actorRepo.findById(o.getActorId());
    }

    @Transactional(readOnly = false)
    @Override
    public Optional<Actor> update(Integer actorId, Actor saveActor) {
        saveActor.setActorId(actorId);
        Optional<Actor> actorOptional = actorRepo.findById(saveActor.getActorId());
        if (actorOptional.isPresent()) {
            Actor actor = actorOptional.get();
            actor.setFirstName(saveActor.getFirstName());
            actor.setLastName(saveActor.getLastName());
            actor.setLastUpdate(saveActor.getLastUpdate());
            Actor o = actorRepo.save(actor);
            return actorRepo.findById(o.getActorId());
        }
        return Optional.empty();
    }
}

