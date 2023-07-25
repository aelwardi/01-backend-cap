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
    private String description;
    private Date dateCreation;
    private Date dateUpdate;
    private Date tempsEstimer;

    @ManyToOne
    @JoinColumn(name = "Cours_id")
    private Cours cours;

    @OneToMany(mappedBy = "chapitre", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Quiz> quiz;

    @OneToMany(mappedBy = "chapitre", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Section> section;

    public Chapitre() {
    }

    public Chapitre(Long id, String titre, String description, Date dateCreation, Date dateUpdate, Date tempsEstimer, Cours cours, List<Quiz> quiz, List<Section> section) {
        this.id = id;
        this.titre = titre;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Date getTempsEstimer() {
        return tempsEstimer;
    }

    public void setTempsEstimer(Date tempsEstimer) {
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
