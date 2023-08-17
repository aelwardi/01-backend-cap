package com.cagemini.lifescience.rest;

import com.cagemini.lifescience.entity.Apprenant;
import com.cagemini.lifescience.entity.ManagerApprenant;
import com.cagemini.lifescience.entity.ManagerApprenantId;
import com.cagemini.lifescience.model.ApiResponse;
import com.cagemini.lifescience.model.ApprenantInfos;
import com.cagemini.lifescience.model.ManagerApprenantDTO;
import com.cagemini.lifescience.service.ManagerApprenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ManagerApprenantController {

    private ManagerApprenantService managerApprenantService;

    @Autowired
    public ManagerApprenantController(ManagerApprenantService managerApprenantService) {
        this.managerApprenantService = managerApprenantService;
    }

    @GetMapping("/manager_apprenant/{adminId}")
    public  List<ManagerApprenantDTO> getManagerApprenant(@PathVariable("adminId") Long adminId) {
        return managerApprenantService.getManagerApprenant(adminId);
    }

    @GetMapping("/manager_apprenant/{adminId}/{managerId}")
    public List<ApprenantInfos> getManagerApprenantNoAssigned(@PathVariable("adminId") Long adminId, @PathVariable("managerId") Long managerId) {
        return managerApprenantService.getManagerApprenantNoAssigned(adminId, managerId);
    }
    @PostMapping("/manager_apprenant")
    public ResponseEntity<ApiResponse> AddManagerApprenant(@RequestParam("apprenantId") Long apprenantId, @RequestParam("managerId") Long managerId) {
        managerApprenantService.addManagerApprenant(apprenantId, managerId);
        return ResponseEntity.ok(new ApiResponse("Assignment added successfully."));
    }
    @DeleteMapping("/manager_apprenant/{apprenantId}/{managerId}")
    public ResponseEntity<ApiResponse> deleteManagerApprenant(
            @PathVariable("apprenantId") Long apprenantId,
            @PathVariable("managerId") Long managerId) {

        ManagerApprenantId id = new ManagerApprenantId(apprenantId, managerId);
        managerApprenantService.deleteManagerApprenant(id);

        return ResponseEntity.ok(new ApiResponse("ManagerApprenant deleted successfully"));
    }
    /*


    @GetMapping("/manager_apprenant/{managerId}/apprenants")
   // public List<Apprenant> getApprenantsByManagerId(@PathVariable Long managerId) {
    public ManagerApprenantDTO getApprenantsByManagerId(@PathVariable Long managerId) {
        return managerApprenantService.getApprenantsByManagerId(managerId);
    }

    @GetMapping("/manager_apprenants")
    public List<Apprenant> getApprenantsByAdminAndManager(@RequestParam("adminId") Long adminId, @RequestParam("managerId") Long managerId) {
        return managerApprenantService.getApprenantsByAdminAndManager(adminId, managerId);
    }

    */
}
