package com.cagemini.lifescience.rest;

import com.cagemini.lifescience.entity.Chapitre;
import com.cagemini.lifescience.entity.Cours;
import com.cagemini.lifescience.entity.Departement;
import com.cagemini.lifescience.service.ChapitreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ChapitreController {
    private ChapitreService chapitreService;

    public ChapitreController(ChapitreService thechapitreService) {

        chapitreService = thechapitreService;
    }

    @GetMapping("/chapitres/{chapitreId}/cours")
    public Cours getCoursByChapitre(@PathVariable Long chapitreId) {
        return chapitreService.getCoursByChapitre(chapitreId);
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

    @PostMapping("/chapitres")
    public Chapitre addChapitre(@RequestBody Chapitre theChapitre){
        theChapitre.setId(0l);
        Chapitre dbChapitre = chapitreService.save(theChapitre);
        return dbChapitre;
    }

    @PutMapping("/chapitres/{id}")
    public Chapitre updateChapitre(@PathVariable Long id, @RequestBody Chapitre theChapitre){
        theChapitre.setId(id);
        return chapitreService.updateChapitre(theChapitre);
    }

    @DeleteMapping("/chapitres/{chapitreId}")
    public String deleteChapitre(@PathVariable Long chapitreId){
        Chapitre theChapitre = chapitreService.findById(chapitreId);

        if(theChapitre == null){
            throw new RuntimeException("the chapitre id not found "+chapitreId);
        }
        chapitreService.deleteById(chapitreId);
        return ("deleted chapitre id :" +chapitreId);

    }

}
