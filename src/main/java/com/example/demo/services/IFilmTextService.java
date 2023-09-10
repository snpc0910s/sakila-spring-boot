package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.FilmText;

public interface IFilmTextService {
    public List<FilmText> findAll();

    public Optional<FilmText> findById(Integer id);

    public boolean exist(Integer id);

    public Optional<FilmText> insert(FilmText saveFilmText);

    public Optional<FilmText> update(Integer filmTextId, FilmText saveFilmText);
}

