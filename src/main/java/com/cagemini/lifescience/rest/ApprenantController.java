package com.cagemini.lifescience.rest;


import com.cagemini.lifescience.entity.Apprenant;
import com.cagemini.lifescience.entity.Departement;
import com.cagemini.lifescience.service.ApprenantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController

public class ApprenantController {


    private ApprenantService apprenantService;


    //quick and dirty :inject Apprenant service

    public ApprenantController (ApprenantService theApprenantService){
        apprenantService=theApprenantService;
    }


    //export "/Apprenants"and return a list of Apprenants
    /*
    @GetMapping("/apprenants")
    public List<Apprenant> findAll (){
        return apprenantService.findAll();
    }*/

//    @GetMapping("/departements")
//    public List<Departement> findAllDepartement (){
//        return apprenantService.getAllDepartement();
//    }

    @GetMapping("/apprenants/{apprenantId}")
    public Apprenant findById(@PathVariable Long apprenantId){
        Apprenant theApprenant = apprenantService.findById(apprenantId);
        if (theApprenant == null){
            throw new RuntimeException("the Apprenant id not found "+apprenantId);
        }
        else
            return theApprenant;
    }

    @PostMapping("/apprenants" )
    public  Apprenant addAprenant(@RequestBody Apprenant theApprenant){
        theApprenant.setId(0L);
        Apprenant dbApprenant = apprenantService.save(theApprenant);
        return dbApprenant;
    }

//        @PutMapping("/apprenants/{apprenantId}")
//    public Apprenant updateApprenant(@RequestBody Apprenant theApprenant){
//            Apprenant dbApprenant = apprenantService.save(theApprenant);
//        return dbApprenant;
//
//    }
    @PutMapping("/apprenants/{id}")
    public Apprenant updateApprenant(@PathVariable Long id,@RequestBody Apprenant theApprenant){
        theApprenant.setId(id);
        return apprenantService.updateApprenant(theApprenant);
    }

    @DeleteMapping("/apprenants/{apprenantId}")
    public  String deleteApprenant(@PathVariable Long apprenantId){
        Apprenant theApprenant = apprenantService.findById(apprenantId);

        //throw exception if null

        if (theApprenant == null){
            throw new RuntimeException("the apprenant id not found "+apprenantId);
        }
        apprenantService.deleteById(apprenantId);
        return ("Deleted apprenant id :" + apprenantId);


    }
    @GetMapping("/apprenants/search")
    public List<Apprenant> searchApprenants(
            @RequestParam("term") String term) {
        return apprenantService.searchByNameOrLastName(term);
    }
    @GetMapping("/apprenants/details")
    public ResponseEntity<Apprenant> getApprenantDetails(
            @RequestParam("adminId") Long adminId,
            @RequestParam("id") Long id) {
        Apprenant apprenant = apprenantService.getApprenantByAdminIdAndApprenantId(adminId, id);
        if (apprenant == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(apprenant);
    }

}
