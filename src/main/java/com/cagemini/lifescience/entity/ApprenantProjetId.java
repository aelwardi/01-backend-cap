package com.cagemini.lifescience.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ApprenantProjetId implements Serializable {
    private Long apprenantId;
    private Long projetId;

    public ApprenantProjetId() {
    }

    public ApprenantProjetId(Long apprenantId, Long projetId) {
        this.apprenantId = apprenantId;
        this.projetId = projetId;
    }

    public Long getApprenantId() {
        return apprenantId;
    }

    public void setApprenantId(Long apprenantId) {
        this.apprenantId = apprenantId;
    }

    public Long getProjetId() {
        return projetId;
    }

    public void setProjetId(Long projetId) {
        this.projetId = projetId;
    }
}
