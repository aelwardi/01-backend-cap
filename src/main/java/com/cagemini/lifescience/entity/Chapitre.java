package com.cagemini.lifescience.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "chapitre")
public class Chapitre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private Date dateCreation;
    private Date dateUpdate;
    private Long tempsEstimer;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Cours_id", nullable = false)
    @JsonIgnore
    private Cours cours;

    @OneToMany(mappedBy = "chapitre")
    @JsonIgnore
    private List<Quiz> quiz;

    @OneToMany(mappedBy = "chapitre")
    @JsonIgnore
    private List<Section> section;

    public Chapitre() {
    }

    public Chapitre(String titre, Date dateCreation, Date dateUpdate, Long tempsEstimer, Cours cours, List<Quiz> quiz, List<Section> section) {
        this.titre = titre;
        this.dateCreation = dateCreation;
        this.dateUpdate = dateUpdate;
        this.tempsEstimer = tempsEstimer;
        this.cours = cours;
        this.quiz = quiz;
        this.section = section;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Long getTempsEstimer() {
        return tempsEstimer;
    }

    public void setTempsEstimer(Long tempsEstimer) {
        this.tempsEstimer = tempsEstimer;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public List<Quiz> getQuiz() {
        return quiz;
    }

    public void setQuiz(List<Quiz> quiz) {
        this.quiz = quiz;
    }

    public List<Section> getSection() {
        return section;
    }

    public void setSection(List<Section> section) {
        this.section = section;
    }
}

