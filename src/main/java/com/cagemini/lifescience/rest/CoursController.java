package com.cagemini.lifescience.rest;
import com.cagemini.lifescience.dao.ProjetRepository;
import com.cagemini.lifescience.entity.Apprenant;
import com.cagemini.lifescience.entity.Chapitre;
import com.cagemini.lifescience.entity.Cours;
import com.cagemini.lifescience.entity.Projet;
import com.cagemini.lifescience.service.CoursService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
public class CoursController {


    private CoursService coursService;
    private final ProjetRepository projetRepository;


    //quick and dirty :inject Cours service

    public CoursController (CoursService theCoursService,ProjetRepository projetRepository){
        coursService=theCoursService;
        this.projetRepository=projetRepository;
    }

    //get all courses by project
    @GetMapping("/cours")
    public List<Cours> getCoursesByProjet(@RequestParam("projetId") Long projetId) {
        // Retrieve the project from the database by its ID
        Projet projet = projetRepository.findById(projetId)
                .orElseThrow(() -> new ResourceNotFoundException("Projet not found with ID: " + projetId));

        // Get the courses associated with the project
        return coursService.getCoursesByProjet(projet);
    }

    @GetMapping("/cours/{coursId}")
    public Cours findById(@PathVariable Long CoursId){
        Cours theCours = coursService.findById(CoursId);
        if (theCours == null){
            throw new RuntimeException("the Cours id not found "+CoursId);
        }
        else
            return theCours;
    }

     //Add a course to a project
    @PostMapping("/cours")
    public Cours addCourseToProjet(@RequestParam("projetId") Long projetId, @RequestBody Cours cours) {
        Projet projet = projetRepository.findById(projetId)
                .orElseThrow(() -> new ResourceNotFoundException("Projet not found with ID: " + projetId));

        cours.setProjet(projet);
        return coursService.save(cours);
    }

    // Update a course associated with a project
//    @PutMapping("/cours/{coursId}")
//    public Cours updateCourse(@PathVariable("coursId") Long coursId, @RequestParam("projetId") Long projetId, @RequestBody Cours updatedCourse) {
//        Projet projet = projetRepository.findById(projetId)
//                .orElseThrow(() -> new ResourceNotFoundException("Projet not found with ID: " + projetId));
//
//        Cours existingCourse = coursService.findById(coursId);
//        existingCourse.setProjet(projet);
//        existingCourse.setTitle(updatedCourse.getTitle());
//        // Update other course properties as needed
//
//        return coursService.save(existingCourse);
//    }
    @PutMapping("/cours/{id}")
    public Cours updateCourse(@PathVariable Long id,@RequestBody Cours theCours){
        theCours.setId(id);
        return coursService.updateCours(theCours);
    }




    @DeleteMapping("/cours/{coursId}")
    public  String deleteCours(@PathVariable Long coursId){
        Cours thecours = coursService.findById(coursId);

        //throw exception if null

        if (thecours == null){
            throw new RuntimeException("the cours id not found "+coursId);
        }
        coursService.deleteById(coursId);
        return ("Deleted cours id :" + coursId);


    }
    @GetMapping("/cours/search")
    public List<Cours> searchCours(
            @RequestParam("term") String term) {
        return coursService.searchByTitle(term);
    }

    @GetMapping("/cours/{courId}/chapitres")
    public List<Chapitre> getChapitresByCour(@PathVariable Long courId) {
        return coursService.getChapitresByCour(courId);
    }
}
