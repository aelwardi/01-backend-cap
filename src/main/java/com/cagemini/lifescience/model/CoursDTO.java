package com.cagemini.lifescience.model;

import com.cagemini.lifescience.entity.Chapitre;
import com.cagemini.lifescience.entity.Cours;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties({"projet"})
public class CoursDTO {
    private Cours cours;
    private List<Chapitre> chapitres;

    public CoursDTO(Cours cours, List<Chapitre> chapitres) {
        this.cours = cours;
        this.chapitres = chapitres;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public List<Chapitre> getChapitres() {
        return chapitres;
    }

    public void setChapitres(List<Chapitre> chapitres) {
        this.chapitres = chapitres;
    }
}
