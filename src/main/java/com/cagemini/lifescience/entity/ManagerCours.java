package com.cagemini.lifescience.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "manager_cours")
public class ManagerCours {
    @EmbeddedId
    private ManagerCoursId id;
    @ManyToOne
    @MapsId("managerId")
    @JoinColumn(name = "manager_id")
    private Manager manager;
    @ManyToOne
    @MapsId("coursId")
    @JoinColumn(name = "cours_id")
    private Cours cours;

    public ManagerCours() {
    }
    public ManagerCours(Manager manager, Cours cours) {
        this.manager = manager;
        this.cours = cours;
        this.id = new ManagerCoursId(manager.getId(), cours.getId());
    }

    public ManagerCoursId getId() {
        return id;
    }

    public void setId(ManagerCoursId id) {
        this.id = id;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManagerCours that = (ManagerCours) o;
        return Objects.equals(manager, that.manager) && Objects.equals(cours, that.cours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manager, cours);
    }
}
