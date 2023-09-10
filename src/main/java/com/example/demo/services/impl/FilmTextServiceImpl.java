package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.FilmText;
import com.example.demo.repo.FilmTextRepo;
import com.example.demo.services.IFilmTextService;

@Service
public class FilmTextServiceImpl implements IFilmTextService {

    @Autowired
    private FilmTextRepo filmTextRepo;

    @Transactional(readOnly = true)
    @Override
    public List<FilmText> findAll() {
        return filmTextRepo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<FilmText> findById(Integer id) {
        return filmTextRepo.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean exist(Integer id) {
        return filmTextRepo.existsById(id);
    }

    @Transactional(readOnly = false)
    @Override
    public Optional<FilmText> insert(FilmText saveFilmText) {
        FilmText filmText = new FilmText();
        filmText.setFilmId(null);
        filmText.setTitle(saveFilmText.getTitle());
        filmText.setDescription(saveFilmText.getDescription());
        FilmText o = filmTextRepo.save(filmText);
        return filmTextRepo.findById(o.getFilmId());
    }

    @Transactional(readOnly = false)
    @Override
    public Optional<FilmText> update(Integer filmTextId, FilmText saveFilmText) {
        saveFilmText.setFilmId(filmTextId);
        Optional<FilmText> filmTextOptional = filmTextRepo.findById(saveFilmText.getFilmId());
        if (filmTextOptional.isPresent()) {
            FilmText filmText = filmTextOptional.get();
            filmText.setTitle(saveFilmText.getTitle());
            filmText.setDescription(saveFilmText.getDescription());
            FilmText o = filmTextRepo.save(filmText);
            return filmTextRepo.findById(o.getFilmId());
        }
        return Optional.empty();
    }
}

