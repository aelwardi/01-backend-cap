package com.cagemini.lifescience.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "projet")
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nameClient;
    @Column(length = 500)
    private String description;

    @Column(name = "photo", columnDefinition = "LONGBLOB")
    private byte[] photo;
    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;

    public Projet() {
    }

    public Projet(String name, String nameClient, String description, byte[] photo, Departement departement) {
        this.name = name;
        this.nameClient = nameClient;
        this.description = description;
        this.photo = photo;
        this.departement = departement;
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

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
