package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Film;
import com.example.demo.entity.Language;
import com.example.demo.exception.ObjectNotFoundException;
import com.example.demo.repo.FilmRepo;
import com.example.demo.repo.LanguageRepo;
import com.example.demo.services.IFilmService;

@Service
public class FilmServiceImpl implements IFilmService {

    @Autowired
    private FilmRepo filmRepo;

    @Autowired
    private LanguageRepo languageRepo;

    @Transactional(readOnly = true)
    @Override
    public List<Film> findAll() {
        return filmRepo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Film> findById(Integer id) {
        return filmRepo.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean exist(Integer id) {
        return filmRepo.existsById(id);
    }

    @Transactional(readOnly = false)
    @Override
    public Optional<Film> insert(Film saveFilm) {
        Optional<Language> oLanguage = languageRepo.findById(saveFilm.getLanguage().getLanguageId());
        Optional<Language> oOriginLanguage = languageRepo.findById(saveFilm.getOriginalLanguage().getLanguageId());
        if(oLanguage.isPresent() && oOriginLanguage.isPresent()) {
                Film film = new Film();
                film.setFilmId(null);
                film.setTitle(saveFilm.getTitle());
                film.setDescription(saveFilm.getDescription());
                film.setReleaseYear(saveFilm.getReleaseYear());
                film.setLanguage(oLanguage.get());
                film.setOriginalLanguage(oOriginLanguage.get());
                film.setRentalDuration(saveFilm.getRentalDuration());
                film.setRentalRate(saveFilm.getRentalRate());
                film.setLength(saveFilm.getLength());
                film.setReplacementCost(saveFilm.getReplacementCost());
                film.setRating(saveFilm.getRating());
                film.setSpecialFeatures(saveFilm.getSpecialFeatures());
                film.setLastUpdate(saveFilm.getLastUpdate());
                Film o = filmRepo.save(film);
                return filmRepo.findById(o.getFilmId());

        }
        throw new ObjectNotFoundException("Language is not exist.");
    }

    @Transactional(readOnly = false)
    @Override
    public Optional<Film> update(Integer filmId, Film saveFilm) {
        Optional<Language> oLanguage = languageRepo.findById(saveFilm.getLanguage().getLanguageId());
        Optional<Language> oOriginLanguage = languageRepo.findById(saveFilm.getOriginalLanguage().getLanguageId());
        saveFilm.setFilmId(filmId);
        Optional<Film> filmOptional = filmRepo.findById(saveFilm.getFilmId());
        if (filmOptional.isPresent() && oLanguage.isPresent() && oOriginLanguage.isPresent()) {
            Film film = filmOptional.get();
            film.setTitle(saveFilm.getTitle());
            film.setDescription(saveFilm.getDescription());
            film.setReleaseYear(saveFilm.getReleaseYear());
            film.setLanguage(oLanguage.get());
            film.setOriginalLanguage(oOriginLanguage.get());
            film.setRentalDuration(saveFilm.getRentalDuration());
            film.setRentalRate(saveFilm.getRentalRate());
            film.setLength(saveFilm.getLength());
            film.setReplacementCost(saveFilm.getReplacementCost());
            film.setRating(saveFilm.getRating());
            film.setSpecialFeatures(saveFilm.getSpecialFeatures());
            film.setLastUpdate(saveFilm.getLastUpdate());
            Film o = filmRepo.save(film);
            return filmRepo.findById(o.getFilmId());
        }
        throw new ObjectNotFoundException("Language is not exist.");
    }

    @Override
    public List<Film> testCustomQuery() {
        // return filmRepo.findByReleaseYearAndRentalDurationBetween(2006, 7, 7);
        // return filmRepo.findByReleaseYearOrLength(2005, 63);
        return filmRepo.findByNameLanguage("GERMAN");
    }
}

