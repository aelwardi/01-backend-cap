package com.cagemini.lifescience.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "cours")
public class Cours {
    @Id
    // @Column(name = "admin_id",unique = true,nullable = false)
    // @GenericGenerator(name = "gen",strategy = "foreign",parameters = {@org.hibernate.annotations.Parameter(name = "property",value = "admin")})
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
    private Projet projet;

    @OneToMany(mappedBy = "cours" , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Chapitre> chapitres;

    public Cours(Long id, String title, String description, Date dateCreate, Date dateMAJ, Date estimateTime, String actor, String url, Projet projet) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateCreate = dateCreate;
        this.dateMAJ = dateMAJ;
        this.estimateTime = estimateTime;
        this.actor = actor;
        this.url = url;
        this.projet = projet;
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

    public Set<Chapitre> getChapitrs(){ return chapitres;}

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

    @Override
    public String toString() {
        return "cours{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dateCreate=" + dateCreate +
                ", dateMAJ=" + dateMAJ +
                ", estimateTime=" + estimateTime +
                ", actor='" + actor + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
