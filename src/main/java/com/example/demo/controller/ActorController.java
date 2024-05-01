package com.example.demo.controller;

import java.util.Optional;

import com.example.demo.base.BaseConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Actor;
import com.example.demo.services.IActorService;

@RestController
@RequestMapping(BaseConst.BASE_API + "/actor")
public class ActorController {

    private static Logger LOGGER = LoggerFactory.getLogger(ActorController.class);

    @Autowired
    private IActorService actorService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(actorService.findAll());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/{actorId}")
    public ResponseEntity<?> findById(@PathVariable("actorId") Integer actorId) {
        try {
            Optional<Actor> oActor = actorService.findById(actorId);
            if (oActor.isPresent())
                return ResponseEntity.ok(oActor.get());
            return new ResponseEntity<>("Actor is not exist", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> insert(@RequestBody Actor actor) {
        try {
            Optional<Actor> oActor = actorService.insert(actor);
            if (oActor.isPresent())
                return ResponseEntity.ok(oActor.get());
            return new ResponseEntity<>("Insert error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping("/{actorId}")
    public ResponseEntity<?> update(@PathVariable("actorId") Integer actorId, @RequestBody Actor actor) {
        try {
            Optional<Actor> oActor = actorService.update(actorId, actor);
            if (oActor.isPresent())
                return ResponseEntity.ok(oActor.get());
            return new ResponseEntity<>("Update error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.BAD_GATEWAY);
        }
    }
}
