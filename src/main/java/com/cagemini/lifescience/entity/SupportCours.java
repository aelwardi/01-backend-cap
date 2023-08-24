package com.cagemini.lifescience.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
@Entity
@Table(name = "support_cours")
public class SupportCours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;
    @CreationTimestamp
    @Column(name = "date_create", nullable = false, updatable = false)
    private Date dateCreate;

    private String pathFile;
    @ManyToOne
    @JoinColumn(name = "chapitre_id")
    private Chapitre chapitre;



    public SupportCours(){

    }

    public SupportCours(String title, Date dateCreate, String pathFile, Chapitre chapitre) {
        this.title = title;
        this.dateCreate = dateCreate;
        this.pathFile = pathFile;
        this.chapitre = chapitre;
    }

    public Chapitre getChapitre() {
        return chapitre;
    }

    public void setChapitre(Chapitre chapitre) {
        this.chapitre = chapitre;
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

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }
}
