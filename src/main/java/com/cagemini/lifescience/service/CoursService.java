package com.cagemini.lifescience.service;


import com.cagemini.lifescience.entity.Cours;

import java.util.List;

public interface CoursService {
    List<Cours> findAll();

    Cours findById(Long theId);

    Cours save(Cours theCours);

    Cours updateCours(Cours theCours);

    List<Cours> searchByTitle(String term);

    void  deleteById(Long theId);



}
