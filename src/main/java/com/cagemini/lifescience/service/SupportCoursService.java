package com.cagemini.lifescience.service;

import com.cagemini.lifescience.entity.Chapitre;

import com.cagemini.lifescience.entity.Cours;
import com.cagemini.lifescience.entity.SupportCours;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

public interface SupportCoursService {
    List<SupportCours> findAll();
    SupportCours findById(Long theId);

    //get support course by chapitre
    SupportCours getSupportCoursesByChapitre(Chapitre chapitre);

    //add support course to chapitre
    SupportCours addSupportCoursTochapitre(Long chapitreId, MultipartFile file, String title);
    SupportCours saveSupportCour(MultipartFile file ,String title ,Long idChapitre) throws IOException;

    SupportCours save(SupportCours theSupportCoursCours);

    //Delete support course
    void  deleteById(Long theId);


}
