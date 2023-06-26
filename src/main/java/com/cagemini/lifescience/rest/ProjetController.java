package com.cagemini.lifescience.rest;

import com.cagemini.lifescience.entity.Projet;
import com.cagemini.lifescience.model.ApiResponse;
import com.cagemini.lifescience.service.ProjetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjetController {

    private ProjetService projetService;

    public ProjetController(ProjetService projetService) {
        this.projetService = projetService;
    }
    @PostMapping("/projets" )
    public Projet addProjet(@RequestBody Projet theProjet){
        theProjet.setId(0L);
        Projet dbProjet = projetService.save(theProjet);
        return dbProjet;
    }

    @PutMapping("/projets/{id}")
    public Projet updateProjet(@PathVariable Long id, @RequestBody Projet theProjet){
        theProjet.setId(id);
        return projetService.updateProjet(theProjet);
    }

    @DeleteMapping("/projets/{projetId}")
    public ApiResponse deleteDelete(@PathVariable Long projetId){
        Projet theProjet = projetService.findById(projetId);

        //throw exception if null

        if (theProjet == null){
            throw new RuntimeException("the project id not found "+projetId);
        }
        projetService.deleteById(projetId);
        return ( new ApiResponse("Deleted project id :" + projetId));
    }
}
