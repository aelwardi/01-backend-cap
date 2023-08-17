package com.cagemini.lifescience.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cours")
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", length = 500)
    private String description;

    private Date dateCreate;
    private Date dateMAJ;
    private Date estimateTime;
    private String actor;
    
    @Column(name = "url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "projet_id")
    @JsonIgnore
    private Projet projet;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    @JsonIgnore
    private Manager manager;

    @OneToMany(mappedBy = "cours" , cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Chapitre> chapitres;


    public Cours(Long id, String title, String description, Date dateCreate, Date dateMAJ, Date estimateTime, String actor, String url, Projet projet, Manager manager, List<Chapitre> chapitres) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateCreate = dateCreate;
        this.dateMAJ = dateMAJ;
        this.estimateTime = estimateTime;
        this.actor = actor;
        this.url = url;
        this.projet = projet;
        this.manager = manager;
        this.chapitres = chapitres;
    }

    public Cours(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public List<Chapitre> getChapitres() {
        return chapitres;
    }

    public void setChapitres(List<Chapitre> chapitres) {
        this.chapitres = chapitres;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateMAJ() {
        return dateMAJ;
    }

    public void setDateMAJ(Date dateMAJ) {
        this.dateMAJ = dateMAJ;
    }

    public Date getEstimateTime() {
        return estimateTime;
    }

    public void setEstimateTime(Date estimateTime) {
        this.estimateTime = estimateTime;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
