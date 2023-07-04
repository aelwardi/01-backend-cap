package com.cagemini.lifescience.service;


import com.cagemini.lifescience.entity.Cours;
import com.cagemini.lifescience.entity.Projet;

import java.util.List;

public interface CoursService {
    List<Cours> findAll();

    //get courses by project
    List<Cours> getCoursesByProjet(Projet projet);

    Cours findById(Long theId);
//add courses to project
    Cours addCoursToProjet(Long projetId, Cours cours);
    Cours save(Cours theCours);

    // update courses by projet
    Cours updateCours(Long coursId, Long projetId, Cours updatedCourse);

    List<Cours> searchByTitle(String term);

    void  deleteById(Long theId);




}
