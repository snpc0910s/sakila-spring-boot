package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Language;
import com.example.demo.repo.LanguageRepo;
import com.example.demo.services.ILanguageService;

@Service
public class LanguageServiceImpl implements ILanguageService {

    @Autowired
    private LanguageRepo languageRepo;

    @Transactional(readOnly = true)
    @Override
    public List<Language> findAll() {
        return languageRepo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Language> findById(Integer id) {
        return languageRepo.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean exist(Integer id) {
        return languageRepo.existsById(id);
    }

    @Transactional(readOnly = false)
    @Override
    public Optional<Language> insert(Language saveLanguage) {
        Language language = new Language();
        language.setLanguageId(null);
        language.setName(saveLanguage.getName());
        language.setLastUpdate(saveLanguage.getLastUpdate());
        Language o = languageRepo.save(language);
        return languageRepo.findById(o.getLanguageId());
    }

    @Transactional(readOnly = false)
    @Override
    public Optional<Language> update(Integer languageId, Language saveLanguage) {
        saveLanguage.setLanguageId(languageId);
        Optional<Language> languageOptional = languageRepo.findById(saveLanguage.getLanguageId());
        if (languageOptional.isPresent()) {
            Language language = languageOptional.get();
            language.setName(saveLanguage.getName());
            language.setLastUpdate(saveLanguage.getLastUpdate());
            Language o = languageRepo.save(language);
            return languageRepo.findById(o.getLanguageId());
        }
        return Optional.empty();
    }
}

