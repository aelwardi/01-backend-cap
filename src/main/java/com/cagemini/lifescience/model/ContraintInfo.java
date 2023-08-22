package com.cagemini.lifescience.model;

import java.util.Date;

public class ContraintInfo {
    private Long id;
    private Date startCourse;
    private Date endCourse;

    public ContraintInfo() {
    }

    public ContraintInfo(Long id, Date startCourse, Date endCourse) {
        this.id = id;
        this.startCourse = startCourse;
        this.endCourse = endCourse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartCourse() {
        return startCourse;
    }

    public void setStartCourse(Date startCourse) {
        this.startCourse = startCourse;
    }

    public Date getEndCourse() {
        return endCourse;
    }

    public void setEndCourse(Date endCourse) {
        this.endCourse = endCourse;
    }
}
