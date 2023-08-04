package com.cagemini.lifescience.rest;

import com.cagemini.lifescience.entity.Admin;
import com.cagemini.lifescience.entity.Manager;
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
    public List<Manager> findAll (){
        return managerService.findAll();
    }

    @GetMapping("/managers/{managerId}")
    public Manager findById(@PathVariable Long managerId){
        Manager theManager = managerService.findById(managerId);
        if (theManager == null){
            throw new RuntimeException("the manager id not found " + managerId);
        }
        else
            return theManager;
    }

    @PostMapping("/managers" )
    public  Manager addManager(@RequestBody Manager theManager){
        theManager.setId(0L);
        Manager dbManager = managerService.save(theManager);
        return dbManager;
    }

    @PutMapping("/managers/{id}")
    public Manager updateAdmin(@PathVariable Long id,@RequestBody Manager theManager){
        theManager.setId(id);
        return managerService.updateManager(theManager);
    }
    @GetMapping("/managers/search")
    public List<Manager> searchManagers(
            @RequestParam("adminId") Long adminId,
            @RequestParam("term") String term) {
        return managerService.searchByNameOrLastName(adminId, term);
    }

    @GetMapping("/managers/details")
    public ResponseEntity<Manager> getManagerDetails(
            @RequestParam("adminId") Long adminId,
            @RequestParam("id") Long id) {
        Manager manager = managerService.getManagerByAdminIdAndManagerId(adminId, id);
        if (manager == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(manager);
    }
}
