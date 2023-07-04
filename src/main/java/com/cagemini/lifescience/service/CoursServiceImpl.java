package com.cagemini.lifescience.service;


import com.cagemini.lifescience.dao.CoursRepository;
import com.cagemini.lifescience.dao.ProjetRepository;
import com.cagemini.lifescience.entity.Chapitre;
import com.cagemini.lifescience.entity.Cours;
import com.cagemini.lifescience.entity.Projet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CoursServiceImpl implements CoursService{

    private CoursRepository coursRepository;
    private final ProjetRepository projetRepository;


    @Autowired
    public CoursServiceImpl(CoursRepository theCoursRepository,ProjetRepository projetRepository){

        coursRepository=theCoursRepository;
        this.projetRepository=projetRepository;
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
        public Cours save(Cours theCours) {
            return coursRepository.save(theCours);
        }


    @Override
    public Cours updateCours(Long coursId, Long projetId, Cours updatedCourse) {
        Projet projet = projetRepository.findById(projetId)
                .orElseThrow(() -> new ResourceNotFoundException("Projet not found with ID: " + projetId));

        Cours existingCourse = coursRepository.findById(coursId)
                .orElseThrow(() -> new ResourceNotFoundException("Cours not found with ID: " + coursId));

        existingCourse.setProjet(projet);
        existingCourse.setTitle(updatedCourse.getTitle());
        // Update other course properties as needed

        return coursRepository.save(existingCourse);
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

        return new ArrayList<>(theCours.getChapitrs());
    }
}
