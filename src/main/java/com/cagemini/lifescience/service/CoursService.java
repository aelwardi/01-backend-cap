package com.cagemini.lifescience.service;



import com.cagemini.lifescience.entity.Chapitre;
import com.cagemini.lifescience.entity.Cours;
import com.cagemini.lifescience.entity.Projet;
import com.cagemini.lifescience.model.CoursDTO;
import com.cagemini.lifescience.model.CoursInfo;
import com.cagemini.lifescience.model.ProjetDTO;

import java.util.List;

public interface CoursService {
    List<Cours> findAll();

    //get courses by project
    List<Cours> getCoursesByProjet(Projet projet);

    Cours findById(Long theId);
    //add courses to project
    Cours addCoursToProjet(Long projetId, Cours cours);
    Cours save(Long managerId, Long projetId, Cours theCours);
    Cours updateCours(Long id, Cours theCours,Long projectId);

    List<Cours> searchByTitle(String term);

    void  deleteById(Long theId);

    List<Chapitre> getChapitresByCour(Long courId);

    CoursDTO getCoursDTOById(Long coursId);
    List<ProjetDTO> getCoursForApprenant(Long apprenantId);
    List<CoursInfo> getCoursByTitleForApprenant(Long apprenantId, String titreCours);

}
