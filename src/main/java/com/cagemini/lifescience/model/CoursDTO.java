package com.cagemini.lifescience.model;

import com.cagemini.lifescience.entity.Chapitre;
import com.cagemini.lifescience.entity.Cours;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties({"projet"})
public class CoursDTO {
    private CoursInfo cours;
    private List<ChapitreInfo> chapitres;

    public CoursDTO() {
    }
    public CoursDTO(CoursInfo cours, List<ChapitreInfo> chapitres) {
        this.cours = cours;
        this.chapitres = chapitres;
    }

    public CoursInfo getCours() {
        return cours;
    }

    public void setCours(CoursInfo cours) {
        this.cours = cours;
    }

    public List<ChapitreInfo> getChapitres() {
        return chapitres;
    }

    public void setChapitres(List<ChapitreInfo> chapitres) {
        this.chapitres = chapitres;
    }
}
