package com.cagemini.lifescience.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "manager_apprenant")
public class ManagerApprenant implements Serializable {
    @EmbeddedId
    private ManagerApprenantId id;

    @ManyToOne
    @MapsId("apprenantId")
    @JoinColumn(name = "apprenant_id")
    private Apprenant apprenant;


    @ManyToOne
    @MapsId("managerId")
    @JoinColumn(name = "manager_id")
    private Manager manager;

    public ManagerApprenant() {
    }

    public ManagerApprenant(Apprenant apprenant, Manager manager) {
        this.apprenant = apprenant;
        this.manager = manager;
        this.id = new ManagerApprenantId(apprenant.getId(), manager.getId());
    }

    public ManagerApprenantId getId() {
        return id;
    }

    public void setId(ManagerApprenantId id) {
        this.id = id;
    }

    public Apprenant getApprenant() {
        return apprenant;
    }

    public void setApprenant(Apprenant apprenant) {
        this.apprenant = apprenant;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManagerApprenant that = (ManagerApprenant) o;
        return Objects.equals(apprenant, that.apprenant) && Objects.equals(manager, that.manager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apprenant, manager);
    }
}
