package com.cagemini.lifescience.rest;

import com.cagemini.lifescience.entity.Admin;
import com.cagemini.lifescience.entity.Manager;
import com.cagemini.lifescience.model.ApiResponse;
import com.cagemini.lifescience.model.ManagerInfos;
import com.cagemini.lifescience.service.ManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ManagerController {

    private ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/managers")
    public List<ManagerInfos> findAll (){
        return managerService.findAll();
    }

    @GetMapping("/managers/{managerId}")
    public ManagerInfos findById(@PathVariable Long managerId){
        ManagerInfos theManager = managerService.getManagerDetails(managerId);
        if (theManager == null){
            throw new RuntimeException("the manager id not found " + managerId);
        }
        return theManager;
    }

    @PostMapping("/managers" )
    public ApiResponse addManager(@RequestParam("adminId") Long adminId, @RequestBody Manager theManager){
        theManager.setId(0L);
        managerService.save(adminId, theManager);
        return new ApiResponse("Manager added!");
    }

    @PutMapping("/managers/{id}")
    public ApiResponse updateAdmin(@RequestParam("adminId") Long adminId, @PathVariable Long id, @RequestBody Manager theManager){
        managerService.updateManager(adminId, id, theManager);
        return new ApiResponse("Manager updated");
    }
    @GetMapping("/managers/search")
    public List<ManagerInfos> searchManagers(
            @RequestParam("adminId") Long adminId,
            @RequestParam("term") String term) {
        return managerService.searchByNameOrLastName(adminId, term);
    }

    @GetMapping("/managers/details")
    public ResponseEntity<ManagerInfos> getManagerDetails(
            @RequestParam("adminId") Long adminId,
            @RequestParam("id") Long id) {
        ManagerInfos manager = managerService.getManagerByAdminIdAndManagerId(adminId, id);
        if (manager == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(manager);
    }
    @PutMapping("/managers/profile/{id}")
    public ApiResponse updateProfile(@PathVariable Long id, @RequestBody Manager theManager) {
        managerService.updateProfile(id, theManager);
        return new ApiResponse("Manager profile updated");
    }
}
