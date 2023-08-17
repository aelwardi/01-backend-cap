package com.cagemini.lifescience.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "apprenant_projet")
public class ApprenantProjet {
    @EmbeddedId
    private ApprenantProjetId id;
    @ManyToOne
    @MapsId("apprenantId")
    @JoinColumn(name = "apprenant_id")
    private Apprenant apprenant;
    @ManyToOne
    @MapsId("projetId")
    @JoinColumn(name = "projetId")
    private Projet projet;

    public ApprenantProjet() {
    }

    public ApprenantProjet(Apprenant apprenant, Projet projet) {
        this.apprenant = apprenant;
        this.projet = projet;
        this.id = new ApprenantProjetId(apprenant.getId(), projet.getId());
    }

    public ApprenantProjetId getId() {
        return id;
    }

    public void setId(ApprenantProjetId id) {
        this.id = id;
    }

    public Apprenant getApprenant() {
        return apprenant;
    }

    public void setApprenant(Apprenant apprenant) {
        this.apprenant = apprenant;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApprenantProjet that = (ApprenantProjet) o;
        return Objects.equals(apprenant, that.apprenant) && Objects.equals(projet, that.projet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apprenant, projet);
    }
}
