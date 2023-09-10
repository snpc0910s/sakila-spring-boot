package com.example.demo.controller;

import java.util.Optional;

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

import com.example.demo.entity.Film;
import com.example.demo.services.IFilmService;

@RestController
@RequestMapping("/api/film")
public class FilmController {

    @Autowired
    private IFilmService filmService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(filmService.findAll());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }
    @GetMapping("/test")
    public ResponseEntity<?> testCustomQuery() {
        try {
            return ResponseEntity.ok(filmService.testCustomQuery());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }
    @GetMapping("/{filmId}")
    public ResponseEntity<?> findById(@PathVariable("filmId") Integer filmId) {
        try {
            Optional<Film> oFilm = filmService.findById(filmId);
            if (oFilm.isPresent())
                return ResponseEntity.ok(oFilm.get());
            return new ResponseEntity<>("Film is not exist", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> insert(@RequestBody Film film) {
        try {
            Optional<Film> oFilm = filmService.insert(film);
            if (oFilm.isPresent())
                return ResponseEntity.ok(oFilm.get());
            return new ResponseEntity<>("Insert error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping("/{filmId}")
    public ResponseEntity<?> update(@PathVariable("filmId") Integer filmId, @RequestBody Film film) {
        try {
            Optional<Film> oFilm = filmService.update(filmId, film);
            if (oFilm.isPresent())
                return ResponseEntity.ok(oFilm.get());
            return new ResponseEntity<>("Update error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.BAD_GATEWAY);
        }
    }
}
