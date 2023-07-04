package com.cagemini.lifescience.service;

import com.cagemini.lifescience.entity.Projet;

import java.util.List;

public interface ProjetService {
    Projet save(Projet theProjet);
    Projet updateProjet(Projet theProjet);
    void  deleteById(Long theId);
    Projet findById(Long theId);
    List<Projet> searchByNameOrNameClientAndDepartementId(String term, Long departementId);
}
