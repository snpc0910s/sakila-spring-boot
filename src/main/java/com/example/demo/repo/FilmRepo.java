package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.demo.repo.custom.FilmRepoCustom;
import com.example.demo.entity.Film;

@Repository
public interface FilmRepo extends JpaRepository<Film,Integer> , FilmRepoCustom{
}
