package com.cagemini.lifescience.service;

import com.cagemini.lifescience.entity.Chapitre;

import java.util.List;

public interface ChapitreService {

    List<Chapitre> findAll();
    Chapitre findById(Long theId);
    Chapitre save(Long coursId,Chapitre theChapitre);
    Chapitre updateChapitre(Chapitre theChapitre);
    void deleteById(Long theId, Long coursId);

    //List<Section> getSectionByChapitre(Long ChapitreId);
    //List<Quiz> getQuizByChapitre(Long ChapitreId);
}
