package com.cagemini.lifescience.model;

import com.cagemini.lifescience.entity.Chapitre;
import com.cagemini.lifescience.entity.Section;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class ChapitreDTO {

    private ChapitreInfo chapitre;
    private List<SectionInfo> sections;

    public ChapitreDTO() {}

    public ChapitreDTO(ChapitreInfo chapitre, List<SectionInfo> sections) {
        this.chapitre = chapitre;
        this.sections = sections;
    }

    public ChapitreInfo getChapitre() {
        return chapitre;
    }

    public void setChapitre(ChapitreInfo chapitre) {
        this.chapitre = chapitre;
    }

    public List<SectionInfo> getSections() {
        return sections;
    }

    public void setSections(List<SectionInfo> sections) {
        this.sections = sections;
    }
}
