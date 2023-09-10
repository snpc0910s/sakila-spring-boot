package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Film;

@Repository
public interface FilmRepo extends JpaRepository<Film,Integer>{

    @Query(value = "select f from Film f where f.releaseYear = ?1 and f.rentalDuration >= ?2 and f.rentalDuration <= ?3")
    List<Film> findByReleaseYearAndRentalDurationBetween(Integer releaseYear, Integer startRental, Integer endRental);

    @Query(value = "select * from film f where f.release_year = :releaseYear or f.length = :length", nativeQuery = true)
    List<Film> findByReleaseYearOrLength(Integer releaseYear, Integer length);

    @Query(value = "select f.* from film f inner join language l on f.language_id = l.language_id where l.name = :nameLanguage", nativeQuery = true)
    List<Film> findByNameLanguageNativeQuery(String nameLanguage);

    @Query(value = "select f from Film f join f.language l where lower(l.name) = :nameLanguage")
    List<Film> findByNameLanguage(String nameLanguage);
}
