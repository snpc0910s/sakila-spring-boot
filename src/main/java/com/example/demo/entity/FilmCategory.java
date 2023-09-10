package com.example.demo.entity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.MapsId;

@Entity
@Table(name = "film_category")
public class FilmCategory {

    // static class
    @Embeddable
    public static class FilmCategoryId implements Serializable {
        @Column(name = "film_id")
        private Integer filmId;
        @Column(name = "category_id")
        private Integer categoryId;


        public FilmCategoryId(){}

        public FilmCategoryId(Integer filmId,Integer categoryId) {
            this.filmId = filmId;
            this.categoryId = categoryId;
        }
        

		// getter and setter 2 key
        public Integer getFilmId() {
            return filmId;
        }
        public void setFilmId(Integer filmId) {
            this.filmId = filmId;
        }
        public Integer getCategoryId() {
            return categoryId;
        }
        public void setCategoryId(Integer categoryId) {
            this.categoryId = categoryId;
        }


        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((filmId == null) ? 0 : filmId.hashCode());
            result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            FilmCategoryId other = (FilmCategoryId) obj;
            if (filmId == null) {
                if (other.filmId != null)
                    return false;
            } else if (!filmId.equals(other.filmId))
                return false;
            if (categoryId == null) {
                if (other.categoryId != null)
                    return false;
            } else if (!categoryId.equals(other.categoryId))
                return false;
            return true;
        }
    }
    @EmbeddedId
    private FilmCategoryId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("filmId")
    @JoinColumn(name = "film_id")
    private Film film;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("categoryId")
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "last_update")
    private Date lastUpdate;

    // getter and setter
    public Film getFilm() {
        return film;
    }
    public void setFilm(Film film) {
        this.film = film;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public Date getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    public FilmCategoryId getId() {
        return id;
    }

    public void setId(FilmCategoryId id) {
        this.id = id;
    }
}