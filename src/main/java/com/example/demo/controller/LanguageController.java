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

import com.example.demo.entity.Language;
import com.example.demo.services.ILanguageService;

@RestController
@RequestMapping("/api/language")
public class LanguageController {

    @Autowired
    private ILanguageService languageService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(languageService.findAll());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/{languageId}")
    public ResponseEntity<?> findById(@PathVariable("languageId") Integer languageId) {
        try {
            Optional<Language> oLanguage = languageService.findById(languageId);
            if (oLanguage.isPresent())
                return ResponseEntity.ok(oLanguage.get());
            return new ResponseEntity<>("Language is not exist", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> insert(@RequestBody Language language) {
        try {
            Optional<Language> oLanguage = languageService.insert(language);
            if (oLanguage.isPresent())
                return ResponseEntity.ok(oLanguage.get());
            return new ResponseEntity<>("Insert error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping("/{languageId}")
    public ResponseEntity<?> update(@PathVariable("languageId") Integer languageId, @RequestBody Language language) {
        try {
            Optional<Language> oLanguage = languageService.update(languageId, language);
            if (oLanguage.isPresent())
                return ResponseEntity.ok(oLanguage.get());
            return new ResponseEntity<>("Update error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.BAD_GATEWAY);
        }
    }
}
