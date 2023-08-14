package com.cagemini.lifescience.rest;

import com.cagemini.lifescience.entity.ApprenantProjetId;
import com.cagemini.lifescience.model.ApiResponse;
import com.cagemini.lifescience.model.ApprenantInfo;
import com.cagemini.lifescience.service.ApprenantProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApprenantProjetController {
    private ApprenantProjetService apprenantProjetService;
    @Autowired

    public ApprenantProjetController(ApprenantProjetService apprenantProjetService) {
        this.apprenantProjetService = apprenantProjetService;
    }
    @GetMapping("/apprenant_projet/{projetId}/apprenants")
    public ResponseEntity<List<ApprenantInfo>> getApprenantsByprojetId(@PathVariable("projetId") Long projetId){
        List<ApprenantInfo> apprenantInfos = apprenantProjetService.getApprenantsByprojetId(projetId);
        return ResponseEntity.ok(apprenantInfos);
    }
    @DeleteMapping("/apprenant_projet/{apprenantId}/{projetId}")
    public ResponseEntity<ApiResponse> deleteApprenantProjet(@PathVariable("apprenantId") Long apprenantId, @PathVariable("projetId") Long projetId ){
        ApprenantProjetId apprenantProjetId = new ApprenantProjetId(apprenantId, projetId);
        apprenantProjetService.deleteApprenantProjet(apprenantProjetId);
        return ResponseEntity.ok(new ApiResponse("ApprenantProjet deleted successfully"));
    }
    @PostMapping("/apprenant_projet")
    public ResponseEntity<ApiResponse> addApprenantProjet(@RequestParam("apprenantId") Long apprenantId, @RequestParam("projetId") Long projetId){
        apprenantProjetService.addApprenantProjet(apprenantId, projetId);
        return ResponseEntity.ok(new ApiResponse("Assignment added successfully."));
    }
    @GetMapping("/apprenant_projet/{managerId}/{projetId}")
    public ResponseEntity<List<ApprenantInfo>> getApprenantsByManagerAndNotInProject(@PathVariable("managerId") Long managerId, @PathVariable("projetId") Long projetId){
        List<ApprenantInfo> apprenantInfos = apprenantProjetService.getApprenantsByManagerAndNotInProject(managerId,projetId);
        return ResponseEntity.ok(apprenantInfos);
    }
}
