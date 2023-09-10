package com.example.demo.entity;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "language")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Integer languageId;

    @Column(name = "name")
    private String name;

    @Column(name = "last_update")
    private Date lastUpdate;

    @OneToMany(mappedBy = "language")
    private List<Film> lstFilm = new ArrayList<>();

    @OneToMany(mappedBy = "originalLanguage")
    private List<Film> lstFilm1 = new ArrayList<>();

    // getter and setter for @OneToMany
    public List<Film> getLstFilm() {
        return lstFilm;
    }
    public void setLstFilm(List<Film> lstFilm) {
        this.lstFilm = lstFilm;
    }
    public List<Film> getLstFilm1() {
        return lstFilm1;
    }
    public void setLstFilm1(List<Film> lstFilm1) {
        this.lstFilm1 = lstFilm1;
    }

    // getter and setter
    public Integer getLanguageId() {
        return languageId;
    }
    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}