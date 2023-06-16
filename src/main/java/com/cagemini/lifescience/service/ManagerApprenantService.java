package com.cagemini.lifescience.service;


import com.cagemini.lifescience.entity.Apprenant;
import com.cagemini.lifescience.model.ManagerApprenantDTO;

import java.util.List;

public interface ManagerApprenantService {
    void addManagerApprenant(Long apprenantId, Long managerId);
    //List<Apprenant> getApprenantsByManagerId(Long managerId);
    ManagerApprenantDTO getApprenantsByManagerId(Long managerId);
}
