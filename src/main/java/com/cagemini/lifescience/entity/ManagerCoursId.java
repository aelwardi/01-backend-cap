package com.cagemini.lifescience.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ManagerCoursId implements Serializable {
    private Long managerId;
    private Long coursId;
    public ManagerCoursId(){}
    public ManagerCoursId(Long managerId, Long coursId){
        this.managerId = managerId;
        this.coursId = coursId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getCoursId() {
        return coursId;
    }

    public void setCoursId(Long coursId) {
        this.coursId = coursId;
    }
}
