package com.example.demo.controller;

import java.util.Optional;

import com.example.demo.base.BaseConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.FilmText;
import com.example.demo.services.IFilmTextService;

@RestController
@RequestMapping(BaseConst.BASE_API + "/filmText")
public class FilmTextController {

    @Autowired
    private IFilmTextService filmTextService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(filmTextService.findAll());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/{filmTextId}")
    public ResponseEntity<?> findById(@PathVariable("filmTextId") Integer filmTextId) {
        try {
            Optional<FilmText> oFilmText = filmTextService.findById(filmTextId);
            if (oFilmText.isPresent())
                return ResponseEntity.ok(oFilmText.get());
            return new ResponseEntity<>("FilmText is not exist", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> insert(@RequestBody FilmText filmText) {
        try {
            Optional<FilmText> oFilmText = filmTextService.insert(filmText);
            if (oFilmText.isPresent())
                return ResponseEntity.ok(oFilmText.get());
            return new ResponseEntity<>("Insert error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping("/{filmTextId}")
    public ResponseEntity<?> update(@PathVariable("filmTextId") Integer filmTextId, @RequestBody FilmText filmText) {
        try {
            Optional<FilmText> oFilmText = filmTextService.update(filmTextId, filmText);
            if (oFilmText.isPresent())
                return ResponseEntity.ok(oFilmText.get());
            return new ResponseEntity<>("Update error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.BAD_GATEWAY);
        }
    }
}
