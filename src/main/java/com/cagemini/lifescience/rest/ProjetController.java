package com.cagemini.lifescience.rest;

import com.cagemini.lifescience.entity.Projet;
import com.cagemini.lifescience.model.ApiResponse;
import com.cagemini.lifescience.model.ProjetDTO;
import com.cagemini.lifescience.model.ProjetInfos;
import com.cagemini.lifescience.service.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjetController {

    private ProjetService projetService;

    @Autowired
    public ProjetController(ProjetService projetService) {
        this.projetService = projetService;
    }

    @GetMapping("/projets")
    public List<ProjetInfos> getProjetsByDepartement(@RequestParam("managerId") Long managerId){
        return projetService.getProjetsByDepartement(managerId);
    }

    @GetMapping("/projets/search")
    public List<ProjetInfos> searchProjetByNameOrNameClientAndDepartementId(@RequestParam String term, @RequestParam Long managerId) {
        return projetService.searchByNameOrNameClientAndDepartementId(term, managerId);
    }
    @GetMapping("/projects/{id}")
    public Projet getProjectById(@PathVariable Long id){
        Projet projetById = this.projetService.findById(id);
        if (projetById == null){
            throw new RuntimeException("the project id not found "+id);
        }
        return projetById;
    }

    @PostMapping("/projets" )
    public ApiResponse addProjet(@RequestParam("managerId") Long managerId, @RequestBody Projet theProjet){
        theProjet.setId(0L);
        projetService.save(managerId, theProjet);
        return new ApiResponse("Project added!");
    }

    @PutMapping("/projets/{id}")
    public ApiResponse updateProjet(@PathVariable Long id, @RequestBody Projet theProjet){
        projetService.updateProjet(id, theProjet);
        return new ApiResponse("Project updated");
    }

    @DeleteMapping("/projets/{projetId}")
    public ApiResponse deleteProjet(@PathVariable Long projetId){
        Projet theProjet = projetService.findById(projetId);

        if (theProjet == null){
            throw new RuntimeException("the project id not found "+projetId);
        }
        projetService.deleteById(projetId);
        return ( new ApiResponse("Deleted project id :" + projetId));
    }

    @GetMapping("/projets/{managerId}/cours")
    public ResponseEntity<List<ProjetDTO>> getProjetsAndCoursByManagerId(@PathVariable Long managerId) {
        List<ProjetDTO> projetDTOs = projetService.getProjetsAndCoursByManagerId(managerId);
        return ResponseEntity.ok(projetDTOs);
    }
}
