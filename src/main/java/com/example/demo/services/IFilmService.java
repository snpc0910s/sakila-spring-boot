package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Film;

public interface IFilmService {
    public List<Film> findAll();

    public Optional<Film> findById(Integer id);

    public boolean exist(Integer id);

    public Optional<Film> insert(Film saveFilm);

    public Optional<Film> update(Integer filmId, Film saveFilm);
}

