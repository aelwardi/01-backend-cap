package com.cagemini.lifescience.model;

import com.cagemini.lifescience.entity.Chapitre;
import com.cagemini.lifescience.entity.Section;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties("cours")
public class ChapitreDTO {

    private Chapitre chapitre;
    private List<Section> sections;

    public ChapitreDTO() {}

    public ChapitreDTO(Chapitre chapitre, List<Section> sections) {
        this.chapitre = chapitre;
        this.sections = sections;
    }

    public Chapitre getChapitre() {
        return chapitre;
    }

    public void setChapitre(Chapitre chapitre) {
        this.chapitre = chapitre;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
