package com.cagemini.lifescience.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ManagerApprenantId implements Serializable {
    private Long apprenantId;
    private Long managerId;

    public ManagerApprenantId() {
    }

    public ManagerApprenantId(Long apprenantId, Long managerId) {
        this.apprenantId = apprenantId;
        this.managerId = managerId;
    }

    public Long getApprenantId() {
        return apprenantId;
    }

    public void setApprenantId(Long apprenantId) {
        this.apprenantId = apprenantId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManagerApprenantId that = (ManagerApprenantId) o;
        return Objects.equals(apprenantId, that.apprenantId) && Objects.equals(managerId, that.managerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apprenantId, managerId);
    }
}
