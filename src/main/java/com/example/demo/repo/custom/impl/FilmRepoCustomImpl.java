package com.example.demo.repo.custom.impl;
import com.example.demo.entity.Film;
import com.example.demo.entity.QFilm;
import com.example.demo.repo.custom.FilmRepoCustom;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class FilmRepoCustomImpl implements FilmRepoCustom {
    private final JPAQueryFactory queryFactory ;
    private static final QFilm qFilm = QFilm.film;

    public FilmRepoCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Film> dynamicSearch(Film film, Pageable page) {
        JPAQuery<Film> query =  queryFactory.selectFrom(qFilm);
        if(film.getFilmId() != null ) {
            query.where(qFilm.filmId.eq(film.getFilmId()));
        }
        if(film.getTitle() != null ) {
            query.where(qFilm.title.eq(film.getTitle()));
        }
        if(film.getDescription() != null ) {
            query.where(qFilm.description.eq(film.getDescription()));
        }
        if(film.getReleaseYear() != null ) {
            query.where(qFilm.releaseYear.eq(film.getReleaseYear()));
        }
        if(film.getRentalDuration() != null ) {
            query.where(qFilm.rentalDuration.eq(film.getRentalDuration()));
        }
        if(film.getRentalRate() != null ) {
            query.where(qFilm.rentalRate.eq(film.getRentalRate()));
        }
        if(film.getLength() != null ) {
            query.where(qFilm.length.eq(film.getLength()));
        }
        if(film.getReplacementCost() != null ) {
            query.where(qFilm.replacementCost.eq(film.getReplacementCost()));
        }
        if(film.getRating() != null ) {
            query.where(qFilm.rating.eq(film.getRating()));
        }
        if(film.getSpecialFeatures() != null ) {
            query.where(qFilm.specialFeatures.eq(film.getSpecialFeatures()));
        }
        if(film.getLastUpdate() != null ) {
            query.where(qFilm.lastUpdate.eq(film.getLastUpdate()));
        }
        if(page != null) {
            query.offset(page.getPageSize() * page.getPageNumber());
            query.limit(page.getPageSize());
        }
        return query.fetch();
    }
}
