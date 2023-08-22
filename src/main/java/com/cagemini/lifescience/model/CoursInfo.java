package com.cagemini.lifescience.model;

import java.util.Date;

public class CoursInfo {
    private Long id;
    private String title;
    private String description;
    private String actor;
    private Date dateMAJ;
    private byte[] photo;

    public CoursInfo() {
    }

    public CoursInfo(Long id, String title, String description, String actor, Date dateMAJ, byte[] photo) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.actor = actor;
        this.dateMAJ = dateMAJ;
        this.photo = photo;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public Date getDateMAJ() {
        return dateMAJ;
    }

    public void setDateMAJ(Date dateMAJ) {
        this.dateMAJ = dateMAJ;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
