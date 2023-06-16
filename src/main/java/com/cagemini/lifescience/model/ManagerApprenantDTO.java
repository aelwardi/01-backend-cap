package com.cagemini.lifescience.model;

import com.cagemini.lifescience.entity.Apprenant;
import com.cagemini.lifescience.entity.Manager;

import java.util.List;

public class ManagerApprenantDTO {
    private Manager manager;
    private List<Apprenant> apprenants;

    public ManagerApprenantDTO() {
    }
    public ManagerApprenantDTO(Manager manager, List<Apprenant> apprenants) {
        this.manager = manager;
        this.apprenants = apprenants;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public List<Apprenant> getApprenants() {
        return apprenants;
    }

    public void setApprenants(List<Apprenant> apprenants) {
        this.apprenants = apprenants;
    }
}
