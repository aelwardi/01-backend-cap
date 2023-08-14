package com.cagemini.lifescience.service;

import com.cagemini.lifescience.entity.ApprenantProjetId;
import com.cagemini.lifescience.model.ApprenantInfo;

import java.util.List;

public interface ApprenantProjetService {
    List<ApprenantInfo> getApprenantsByprojetId(Long projetId);
    void deleteApprenantProjet(ApprenantProjetId apprenantProjetId);
    void  addApprenantProjet(Long apprenantId, Long projetId);
    List<ApprenantInfo> getApprenantsByManagerAndNotInProject(Long managerId, Long projetId);
}
