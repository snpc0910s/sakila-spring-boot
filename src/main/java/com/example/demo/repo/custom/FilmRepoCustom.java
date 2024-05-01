package com.example.demo.repo.custom;
import java.util.List;
import org.springframework.data.domain.Pageable;
import com.example.demo.entity.Film;

public interface FilmRepoCustom {
    public List<Film> dynamicSearch(Film film, Pageable page);
}
