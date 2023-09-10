package com.example.demo.entity;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Integer filmId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private Integer releaseYear;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    private Language language;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "original_language_id")
    private Language originalLanguage;

    @Column(name = "rental_duration")
    private Integer rentalDuration;

    @Column(name = "rental_rate")
    private Double rentalRate;

    @Column(name = "length")
    private Integer length;

    @Column(name = "replacement_cost")
    private Double replacementCost;

    @Column(name = "rating")
    private String rating;

    @Column(name = "special_features")
    private String specialFeatures;

    @Column(name = "last_update")
    private Date lastUpdate;

    @OneToMany(mappedBy = "film")
    private List<FilmCategory> lstFilmCategory = new ArrayList<>();

    @OneToMany(mappedBy = "film")
    private List<FilmActor> lstFilmActor = new ArrayList<>();

    @OneToMany(mappedBy = "film")
    private List<Inventory> lstInventory = new ArrayList<>();

    // getter and setter for @OneToMany
    public List<FilmCategory> getLstFilmCategory() {
        return lstFilmCategory;
    }
    public void setLstFilmCategory(List<FilmCategory> lstFilmCategory) {
        this.lstFilmCategory = lstFilmCategory;
    }
    public List<FilmActor> getLstFilmActor() {
        return lstFilmActor;
    }
    public void setLstFilmActor(List<FilmActor> lstFilmActor) {
        this.lstFilmActor = lstFilmActor;
    }
    public List<Inventory> getLstInventory() {
        return lstInventory;
    }
    public void setLstInventory(List<Inventory> lstInventory) {
        this.lstInventory = lstInventory;
    }

    // getter and setter
    public Integer getFilmId() {
        return filmId;
    }
    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getReleaseYear() {
        return releaseYear;
    }
    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }
    public Language getLanguage() {
        return language;
    }
    public void setLanguage(Language language) {
        this.language = language;
    }
    public Language getOriginalLanguage() {
        return originalLanguage;
    }
    public void setOriginalLanguage(Language originalLanguage) {
        this.originalLanguage = originalLanguage;
    }
    public Integer getRentalDuration() {
        return rentalDuration;
    }
    public void setRentalDuration(Integer rentalDuration) {
        this.rentalDuration = rentalDuration;
    }
    public Double getRentalRate() {
        return rentalRate;
    }
    public void setRentalRate(Double rentalRate) {
        this.rentalRate = rentalRate;
    }
    public Integer getLength() {
        return length;
    }
    public void setLength(Integer length) {
        this.length = length;
    }
    public Double getReplacementCost() {
        return replacementCost;
    }
    public void setReplacementCost(Double replacementCost) {
        this.replacementCost = replacementCost;
    }
    public String getRating() {
        return rating;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }
    public String getSpecialFeatures() {
        return specialFeatures;
    }
    public void setSpecialFeatures(String specialFeatures) {
        this.specialFeatures = specialFeatures;
    }
    public Date getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}