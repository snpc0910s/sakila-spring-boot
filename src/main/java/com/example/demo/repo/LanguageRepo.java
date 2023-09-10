package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Language;

@Repository
public interface LanguageRepo extends JpaRepository<Language,Integer>{
}
