package com.cagemini.lifescience.model;

public class DepartementInfo {
    private Long id;
    private String name;

    public DepartementInfo() {
    }

    public DepartementInfo(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
