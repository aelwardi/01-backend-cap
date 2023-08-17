package com.cagemini.lifescience.service;

import com.cagemini.lifescience.entity.Projet;
import com.cagemini.lifescience.model.ProjetDTO;
import com.cagemini.lifescience.model.ProjetInfos;

import java.util.List;

public interface ProjetService {
    Projet save(Long managerId, Projet theProjet);
    Projet updateProjet(Long id, Projet theProjet);
    void  deleteById(Long theId);
    Projet findById(Long theId);
    List<ProjetInfos> searchByNameOrNameClientAndDepartementId(String term, Long managerId);
    List<ProjetDTO> getProjetsAndCoursByManagerId(Long managerId);

    List<ProjetInfos> getProjetsByDepartement(Long managerId);
}
