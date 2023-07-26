package com.cagemini.lifescience.service;

import com.cagemini.lifescience.entity.Chapitre;

import java.util.List;

public interface ChapitreService {

    List<Chapitre> findAll();
    Chapitre findById(Long theId);
    Chapitre save(Long coursId,Chapitre theChapitre);
    Chapitre addChapitreToCours(Long coursId, Chapitre chapitre);
    Chapitre updateChapitre(Chapitre theChapitre, Long theId);
    void deleteById(Long theId, Long coursId);
    List<Chapitre> getChapitreByCoursId(Long coursId);

    //List<Section> getSectionByChapitre(Long ChapitreId);
    //List<Quiz> getQuizByChapitre(Long ChapitreId);
}
