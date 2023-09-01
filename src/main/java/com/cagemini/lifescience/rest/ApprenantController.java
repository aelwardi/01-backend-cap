package com.cagemini.lifescience.rest;


import com.cagemini.lifescience.entity.Apprenant;
import com.cagemini.lifescience.entity.Departement;
import com.cagemini.lifescience.entity.Manager;
import com.cagemini.lifescience.model.ApiResponse;
import com.cagemini.lifescience.model.ApprenantInfos;
import com.cagemini.lifescience.service.ApprenantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController

public class ApprenantController {


    private ApprenantService apprenantService;


    public ApprenantController (ApprenantService theApprenantService){
        apprenantService=theApprenantService;
    }


    @GetMapping("/apprenants/{apprenantId}")
    public ApprenantInfos findById(@PathVariable Long apprenantId){
        ApprenantInfos theApprenant = apprenantService.getApprenantDetails(apprenantId);
        if (theApprenant == null){
            throw new RuntimeException("the Apprenant id not found "+apprenantId);
        }
        return theApprenant;
    }

    @PostMapping("/apprenants" )
    public ApiResponse addAprenant(@RequestParam("adminId") Long adminId, @RequestBody Apprenant theApprenant){
        theApprenant.setId(0L);
        apprenantService.save(adminId, theApprenant);
        return new ApiResponse("Apprenant added!");
    }

    @PutMapping("/apprenants/{id}")
    public ApiResponse updateApprenant(@RequestParam("adminId") Long adminId, @PathVariable Long id,@RequestBody Apprenant theApprenant){
        apprenantService.updateApprenant(adminId, id, theApprenant);
        return new ApiResponse("Apprenant updated");
    }

    @DeleteMapping("/apprenants/{apprenantId}")
    public  String deleteApprenant(@PathVariable Long apprenantId){
        Apprenant theApprenant = apprenantService.findById(apprenantId);

        if (theApprenant == null){
            throw new RuntimeException("the apprenant id not found "+apprenantId);
        }
        apprenantService.deleteById(apprenantId);
        return ("Deleted apprenant id :" + apprenantId);


    }
    @GetMapping("/apprenants/search")
    public List<ApprenantInfos> searchApprenants(
            @RequestParam("adminId") Long adminId,
            @RequestParam("term") String term) {
        return apprenantService.searchByNameOrLastName(adminId, term);
    }
    @GetMapping("/apprenants/details")
    public ResponseEntity<ApprenantInfos> getApprenantDetails(
            @RequestParam("adminId") Long adminId,
            @RequestParam("id") Long id) {
        ApprenantInfos apprenant = apprenantService.getApprenantByAdminIdAndApprenantId(adminId, id);
        if (apprenant == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(apprenant);
    }

    @PutMapping("/apprenants/profile/{id}")
    public ApiResponse updateProfile(@PathVariable Long id, @RequestBody Apprenant theApprenant) {
        apprenantService.updateProfile(id, theApprenant);
        return new ApiResponse("Consultant profile updated");
    }

}
