package com.cagemini.lifescience.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "contraint")
public class Contraint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date startCourse;
    private Date endCourse;
    @OneToOne
    @JoinColumn(name = "cours_id")
    private Cours cours;

    public Contraint() {
    }

    public Contraint(Long id, Date startCourse, Date endCourse, Cours cours) {
        this.id = id;
        this.startCourse = startCourse;
        this.endCourse = endCourse;
        this.cours = cours;
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

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }
}
