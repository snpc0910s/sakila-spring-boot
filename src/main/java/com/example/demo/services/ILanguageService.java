package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Language;

public interface ILanguageService {
    public List<Language> findAll();

    public Optional<Language> findById(Integer id);

    public boolean exist(Integer id);

    public Optional<Language> insert(Language saveLanguage);

    public Optional<Language> update(Integer languageId, Language saveLanguage);
}

