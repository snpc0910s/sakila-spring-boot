package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.demo.repo.custom.LanguageRepoCustom;
import com.example.demo.entity.Language;

@Repository
public interface LanguageRepo extends JpaRepository<Language,Integer> , LanguageRepoCustom{
}
