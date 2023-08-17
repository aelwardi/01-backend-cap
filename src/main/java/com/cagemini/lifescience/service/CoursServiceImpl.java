package com.cagemini.lifescience.service;


import com.cagemini.lifescience.dao.ChapitreRepository;
import com.cagemini.lifescience.dao.CoursRepository;
import com.cagemini.lifescience.dao.ManagerRepository;
import com.cagemini.lifescience.dao.ProjetRepository;

import com.cagemini.lifescience.entity.*;
import com.cagemini.lifescience.model.CoursDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class CoursServiceImpl implements CoursService{

    private final CoursRepository coursRepository;
    private final ProjetRepository projetRepository;
    private final ChapitreRepository chapitreRepository;
    private final ManagerRepository managerRepository;

    @Autowired
    public CoursServiceImpl(CoursRepository theCoursRepository,ProjetRepository projetRepository, ChapitreRepository chapitreRepository, ManagerRepository managerRepository){

        this.coursRepository=theCoursRepository;
        this.projetRepository=projetRepository;
        this.chapitreRepository=chapitreRepository;
        this.managerRepository = managerRepository;
    }
    @Autowired
    public List<Cours> findAll(){

        return coursRepository.findAll();
    }
    @Override
    public Cours findById(Long theId) {
        Optional<Cours> result=coursRepository.findById(theId);

        Cours theCours = null;
        if (result.isPresent()){
            theCours = result.get();
        }
        else {
            throw new RuntimeException("didn't find cours id:"+theId);
        }
        return theCours;
    }

    //get courses by project:
    @Override
    public List<Cours> getCoursesByProjet(Projet projet) {
        return coursRepository.findByProjet(projet);
        }
    //add Course To Projet
    @Override
    public Cours addCoursToProjet(Long projetId, Cours cours) {
        Projet projet = projetRepository.findById(projetId)
                .orElseThrow(() -> new ResourceNotFoundException("Projet not found with ID: " + projetId));

        cours.setProjet(projet);
        return coursRepository.save(cours);
    }

        @Override
        public Cours save(Long managerId, Long projetId, Cours theCours) {
            Projet projet = projetRepository.findById(projetId)
                    .orElseThrow(() -> new ResourceNotFoundException("Projet not found with ID: " + projetId));
            Manager manager = managerRepository.findById(managerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found with ID: " + managerId));
            theCours.setProjet(projet);
            theCours.setManager(manager);
            theCours.setDateCreate(new Date());
            theCours.setDateMAJ(new Date());
            return coursRepository.save(theCours);
        }


//    @Override
//    public Cours updateCours(Long coursId, Long projetId, Cours updatedCourse) {
//        Projet projet = projetRepository.findById(projetId)
//                .orElseThrow(() -> new ResourceNotFoundException("Projet not found with ID: " + projetId));
//
//        Cours existingCourse = coursRepository.findById(coursId)
//                .orElseThrow(() -> new ResourceNotFoundException("Cours not found with ID: " + coursId));
//
//        existingCourse.setProjet(projet);
//        existingCourse.setTitle(updatedCourse.getTitle());
//        // Update other course properties as needed
//
//        return coursRepository.save(existingCourse);
//    }
@Override
public Cours updateCours(Long id, Cours theCours ,Long projectId) {
    Cours cours = coursRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cours not found with ID: " + id));
    Projet projet = projetRepository.findById(projectId)
            .orElseThrow(() -> new IllegalArgumentException("Projet not found with ID: " + projectId));
    if(!cours.getProjet().getId().equals(projectId)){
        throw new IllegalArgumentException("Cours with ID " + id + " is not associated with Projet with ID " + projectId);
    }
    cours.setTitle(theCours.getTitle());
    cours.setDescription(theCours.getDescription());
    cours.setDateMAJ(new Date());
    return coursRepository.save(cours);
}


    @Override
    public List<Cours> searchByTitle(@RequestParam("term") String term) {
        return coursRepository.searchByTitle(term);
    }

    @Override
    public void deleteById(Long theId) {
        coursRepository.deleteById(theId);

    }

    @Override
    public List<Chapitre> getChapitresByCour(Long courId) {
        Cours theCours = coursRepository.findById(courId)
                .orElseThrow(() -> new IllegalArgumentException("Cours not found with ID: " + courId));

        return new ArrayList<>(theCours.getChapitres());
    }

    @Override
    public CoursDTO getCoursDTOById(Long coursId) {
        Cours theCours = coursRepository.findById(coursId)
                .orElseThrow(() -> new IllegalArgumentException("Cours not found with ID: " + coursId));
        List<Chapitre> chapitres = chapitreRepository.findByCoursId(coursId);
        CoursDTO coursDTO = new CoursDTO(theCours, chapitres);
        return coursDTO;
    }
}
