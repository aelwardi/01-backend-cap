package com.cagemini.lifescience.model;

import jakarta.persistence.Column;

public class ProjetInfos {
    private Long id;
    private String name;
    private String nameClient;
    @Column(length = 500)
    private String description;
    private byte[] photo;

    public ProjetInfos() {
    }

    public ProjetInfos(Long id, String name, String nameClient, String description, byte[] photo) {
        this.id = id;
        this.name = name;
        this.nameClient = nameClient;
        this.description = description;
        this.photo = photo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
