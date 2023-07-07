package com.cagemini.lifescience.service;

import com.cagemini.lifescience.entity.Chapitre;
import com.cagemini.lifescience.entity.Quiz;

import java.util.List;

public interface ChapitreService {

    List<Chapitre> findAll();
    Chapitre findById(Long theId);
    Chapitre save(Chapitre theChapitre);
    Chapitre updateChapitre(Chapitre theChapitre);
    void deleteById(Long theId);
    List<Quiz> getQuizByChapite(Long chapitreId);


    //List<Section> getSectionByChapitre(Long ChapitreId);
    //List<Quiz> getQuizByChapitre(Long ChapitreId);
}
