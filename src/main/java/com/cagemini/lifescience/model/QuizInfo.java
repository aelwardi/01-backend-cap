package com.cagemini.lifescience.model;

import java.util.Date;

public class QuizInfo {
    private Long id;
    private String question;
    private Date dateCreated;
    private Date dateUpdated;

    public QuizInfo() {
    }

    public QuizInfo(Long id, String question, Date dateCreated, Date dateUpdated) {
        this.id = id;
        this.question = question;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
}
