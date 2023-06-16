package com.cagemini.lifescience.rest;

import com.cagemini.lifescience.entity.Apprenant;
import com.cagemini.lifescience.entity.ManagerApprenant;
import com.cagemini.lifescience.entity.ManagerApprenantId;
import com.cagemini.lifescience.model.ManagerApprenantDTO;
import com.cagemini.lifescience.service.ManagerApprenantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")

public class ManagerApprenantController {

    private ManagerApprenantService managerApprenantService;

    public ManagerApprenantController(ManagerApprenantService managerApprenantService) {
        this.managerApprenantService = managerApprenantService;
    }

    @PostMapping("/manager_apprenant")
    public ResponseEntity<String> AddManagerApprenant(@RequestParam("apprenantId") Long apprenantId, @RequestParam("managerId") Long managerId) {
        managerApprenantService.addManagerApprenant(apprenantId, managerId);
        return ResponseEntity.ok("assignment done.");
    }

    @GetMapping("/manager_apprenant/{managerId}/apprenants")
   // public List<Apprenant> getApprenantsByManagerId(@PathVariable Long managerId) {
    public ManagerApprenantDTO getApprenantsByManagerId(@PathVariable Long managerId) {
        return managerApprenantService.getApprenantsByManagerId(managerId);
    }
}
