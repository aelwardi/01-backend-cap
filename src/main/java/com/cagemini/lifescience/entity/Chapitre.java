package com.cagemini.lifescience.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "chapitre")
public class Chapitre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Titre;
    private Date Date_Creation;
    private Date Date_update;
    private Long Temps_Estimer;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Cours_id", nullable = false)
    private Cours cours;

    public Chapitre() {
    }

    public Chapitre(Long id, String titre, Date date_Creation, Date date_update, Long temps_Estimer) {
        this.id = id;
        Titre = titre;
        Date_Creation = date_Creation;
        Date_update = date_update;
        Temps_Estimer = temps_Estimer;
    }

    public Long getId() {
        return id;
    }

    public String getTitre() {
        return Titre;
    }

    public Date getDate_Creation() {
        return Date_Creation;
    }

    public Date getDate_update() {
        return Date_update;
    }

    public Long getTemps_Estimer() {
        return Temps_Estimer;
    }

    public Cours getCours() {
        return cours;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }

    public void setDate_Creation(Date date_Creation) {
        Date_Creation = date_Creation;
    }

    public void setDate_update(Date date_update) {
        Date_update = date_update;
    }

    public void setTemps_Estimer(Long temps_Estimer) {
        Temps_Estimer = temps_Estimer;
    }
}
