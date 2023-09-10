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
@Table(name = "film_actor")
public class FilmActor {

    // static class
    @Embeddable
    public static class FilmActorId implements Serializable {
        @Column(name = "actor_id")
        private Integer actorId;
        @Column(name = "film_id")
        private Integer filmId;


        public FilmActorId(){}

        public FilmActorId(Integer actorId,Integer filmId) {
            this.actorId = actorId;
            this.filmId = filmId;
        }
        

		// getter and setter 2 key
        public Integer getActorId() {
            return actorId;
        }
        public void setActorId(Integer actorId) {
            this.actorId = actorId;
        }
        public Integer getFilmId() {
            return filmId;
        }
        public void setFilmId(Integer filmId) {
            this.filmId = filmId;
        }


        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((actorId == null) ? 0 : actorId.hashCode());
            result = prime * result + ((filmId == null) ? 0 : filmId.hashCode());
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
            FilmActorId other = (FilmActorId) obj;
            if (actorId == null) {
                if (other.actorId != null)
                    return false;
            } else if (!actorId.equals(other.actorId))
                return false;
            if (filmId == null) {
                if (other.filmId != null)
                    return false;
            } else if (!filmId.equals(other.filmId))
                return false;
            return true;
        }
    }
    @EmbeddedId
    private FilmActorId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("actorId")
    @JoinColumn(name = "actor_id")
    private Actor actor;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("filmId")
    @JoinColumn(name = "film_id")
    private Film film;

    @Column(name = "last_update")
    private Date lastUpdate;

    // getter and setter
    public Actor getActor() {
        return actor;
    }
    public void setActor(Actor actor) {
        this.actor = actor;
    }
    public Film getFilm() {
        return film;
    }
    public void setFilm(Film film) {
        this.film = film;
    }
    public Date getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    public FilmActorId getId() {
        return id;
    }

    public void setId(FilmActorId id) {
        this.id = id;
    }
}