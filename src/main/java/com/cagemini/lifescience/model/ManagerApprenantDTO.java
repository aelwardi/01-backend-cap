package com.cagemini.lifescience.model;

import com.cagemini.lifescience.entity.Apprenant;
import com.cagemini.lifescience.entity.Manager;

import java.util.List;

public class ManagerApprenantDTO {
    private ManagerInfos manager;
    private List<ApprenantInfos> apprenants;

    public ManagerApprenantDTO() {
    }

    public ManagerApprenantDTO(ManagerInfos manager, List<ApprenantInfos> apprenants) {
        this.manager = manager;
        this.apprenants = apprenants;
    }

    public ManagerInfos getManager() {
        return manager;
    }

    public void setManager(ManagerInfos manager) {
        this.manager = manager;
    }

    public List<ApprenantInfos> getApprenants() {
        return apprenants;
    }

    public void setApprenants(List<ApprenantInfos> apprenants) {
        this.apprenants = apprenants;
    }
}
