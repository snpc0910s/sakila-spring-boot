package com.example.demo.dto;
import java.util.Date;

public class FilmDTO {
    private Integer filmId;

    private String title;

    private String description;

    private Integer releaseYear;

    private Integer languageId;

    private Integer originalLanguageId;

    private Integer rentalDuration;

    private Double rentalRate;

    private Integer length;

    private Double replacementCost;

    private String rating;

    private String specialFeatures;

    private Date lastUpdate;

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
    public Integer getLanguageId() {
        return languageId;
    }
    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }
    public Integer getOriginalLanguageId() {
        return originalLanguageId;
    }
    public void setOriginalLanguageId(Integer originalLanguageId) {
        this.originalLanguageId = originalLanguageId;
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
