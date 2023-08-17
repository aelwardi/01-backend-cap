package com.cagemini.lifescience.rest;

import com.cagemini.lifescience.dao.CoursRepository;
import com.cagemini.lifescience.entity.Chapitre;
import com.cagemini.lifescience.model.ApiResponse;
import com.cagemini.lifescience.service.ChapitreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChapitreController {
    private ChapitreService chapitreService;
    private CoursRepository coursRepository;

    public ChapitreController(ChapitreService chapitreService, CoursRepository coursRepository) {
        this.chapitreService = chapitreService;
        this.coursRepository = coursRepository;
    }

    @GetMapping("/chapitres")
    public List<Chapitre> findAll(){

        return chapitreService.findAll();
    }

    @GetMapping("/chapitres/{chapitreId}")
    public Chapitre findById(@PathVariable Long chapitreId){
        Chapitre theChapitre = chapitreService.findById(chapitreId);
        if (theChapitre == null){
            throw new RuntimeException("the Chapitre id not found" +chapitreId);
        }
        else
            return theChapitre;

    }
    @GetMapping("/chapitres/cours/{coursId}")
    public List<Chapitre> getChapitresByCoursId(@PathVariable Long coursId) {
        return chapitreService.getChapitreByCoursId(coursId);
    }
/*
    @PostMapping("/chapitres")
    public Chapitre addChapitre(@RequestParam Long coursId, @RequestBody Chapitre theChapitre){
        theChapitre.setId(0l);
        Chapitre dbChapitre = chapitreService.save(coursId,theChapitre);
        return dbChapitre;
    }
*/
    // Ajouter un chapitre à un cours
    @PostMapping("/chapitres/cours/{coursId}")
    public ApiResponse addChapitreToCours(@PathVariable Long coursId, @RequestBody Chapitre newChapitre) {
        newChapitre.setId(0L);
        chapitreService.addChapitreToCours(coursId, newChapitre);
        return new ApiResponse("Chapter added");
    }

    @PutMapping("/chapitres/{theId}")
    public ApiResponse updateChapitre(@PathVariable Long theId, @RequestBody Chapitre theChapitre){
        chapitreService.updateChapitre(theChapitre, theId);
        return new ApiResponse("Chapter updated");
    }

    @DeleteMapping("/chapitres/{theId}/{coursId}")
    public ApiResponse deleteChapitre(@PathVariable Long theId, @PathVariable Long coursId){
        chapitreService.deleteById(theId, coursId);
        return ( new ApiResponse("Deleted chapitre id :" + theId));
    }
}

