package com.cagemini.lifescience.rest;

import com.cagemini.lifescience.entity.Projet;
import com.cagemini.lifescience.model.ApiResponse;
import com.cagemini.lifescience.service.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200/")
@RestController
public class ProjetController {

    private ProjetService projetService;

    @Autowired
    public ProjetController(ProjetService projetService) {
        this.projetService = projetService;
    }

    @GetMapping("/projets/search")
    public List<Projet> searchProjetByNameOrNameClientAndDepartementId(@RequestParam String term, @RequestParam Long departementId) {
        return projetService.searchByNameOrNameClientAndDepartementId(term, departementId);
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
    public ApiResponse deleteProjet(@PathVariable Long projetId){
        Projet theProjet = projetService.findById(projetId);

        //throw exception if null

        if (theProjet == null){
            throw new RuntimeException("the project id not found "+projetId);
        }
        projetService.deleteById(projetId);
        return ( new ApiResponse("Deleted project id :" + projetId));
    }
}
