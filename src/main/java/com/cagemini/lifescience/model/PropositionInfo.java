package com.cagemini.lifescience.model;

public class PropositionInfo {
    private Long id;
    private String response;
    private Boolean correcte;

    public PropositionInfo() {
    }

    public PropositionInfo(Long id, String response, Boolean correcte) {
        this.id = id;
        this.response = response;
        this.correcte = correcte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Boolean getCorrecte() {
        return correcte;
    }

    public void setCorrecte(Boolean correcte) {
        this.correcte = correcte;
    }
}
