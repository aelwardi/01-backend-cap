package com.cagemini.lifescience.rest;

import com.cagemini.lifescience.entity.Departement;
import com.cagemini.lifescience.entity.Projet;
import com.cagemini.lifescience.model.ApiResponse;
import com.cagemini.lifescience.service.DepartementService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")

public class DepartementController {

    private DepartementService departementService;

    public DepartementController(DepartementService departementService) {
        this.departementService = departementService;
    }

    @GetMapping("departements")
    public List<Departement> findAll (){
        return departementService.findAll();
    }

    @GetMapping("/departements/{departementId}")
    public Departement findById(@PathVariable Long departementId){
        Departement theDepartement = departementService.findById(departementId);
        if (theDepartement == null){
            throw new RuntimeException("the Apprenant id not found "+departementId);
        }
        else
            return theDepartement;
    }

    @PostMapping("/departements" )
    public  Departement addDepartement(@RequestBody Departement theDepartement){
        theDepartement.setId(0L);
        Departement dbDepartement = departementService.save(theDepartement);
        return dbDepartement;
    }

    @PutMapping("/departements/{id}")
    public Departement updateDepartement(@PathVariable Long id,@RequestBody Departement theDepartement){
        theDepartement.setId(id);
        return departementService.updateDepartement(theDepartement);
    }

    @DeleteMapping("/departements/{departementId}")
    public ApiResponse deleteDelete(@PathVariable Long departementId){
        Departement theDepartement = departementService.findById(departementId);

        //throw exception if null

        if (theDepartement == null){
            throw new RuntimeException("the departement id not found "+departementId);
        }
        departementService.deleteById(departementId);
        return ( new ApiResponse("Deleted departement id :" + departementId));
    }

    @GetMapping("/departements/search")
    public Page<Departement> searchDepartements(
            @RequestParam("name") String name, Pageable page
    ) {
        return departementService.findByLastNameContaining(name, page);
    }


    @GetMapping("/departements/{departementId}/projets")
    public List<Projet> getProjetsByDepartement(@PathVariable Long departementId) {
        return departementService.getProjetsByDepartement(departementId);
    }
}
