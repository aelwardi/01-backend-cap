package com.cagemini.lifescience.model;


import java.util.Date;

public class SectionInfo {
    private Long id;
    private String titre;
    private String type;
    private String description;
    private String file;
    private Date dateCreated;
    private Date dateUpdated;
    private Double tempsestimer;

    public SectionInfo() {
    }

    public SectionInfo(Long id, String titre, String type, String description, String file, Date dateCreated, Date dateUpdated, Double tempsestimer) {
        this.id = id;
        this.titre = titre;
        this.type = type;
        this.description = description;
        this.file = file;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.tempsestimer = tempsestimer;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Double getTempsestimer() {
        return tempsestimer;
    }

    public void setTempsestimer(Double tempsestimer) {
        this.tempsestimer = tempsestimer;
    }
}
