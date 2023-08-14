package com.cagemini.lifescience.rest;

import com.cagemini.lifescience.entity.Manager;
import com.cagemini.lifescience.entity.ManagerCoursId;
import com.cagemini.lifescience.model.ApiResponse;
import com.cagemini.lifescience.model.ManagerInfo;
import com.cagemini.lifescience.service.ManagerCoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ManagerCoursController {
    private ManagerCoursService managerCoursService;
    @Autowired

    public ManagerCoursController(ManagerCoursService managerCoursService) {
        this.managerCoursService = managerCoursService;
    }
    @PostMapping("/manager_cours")
    public ResponseEntity<ApiResponse> AddManagerCours(@RequestParam("managerId") Long managerId,@RequestParam("coursId") Long coursId){
        managerCoursService.addManagerCours(managerId, coursId);
        return ResponseEntity.ok(new ApiResponse("Assignment added successfully."));
    }
    @DeleteMapping("/manager_cours/{managerId}/{coursId}")
    public ResponseEntity<ApiResponse> deleteManagerCours(@PathVariable("managerId") Long managerId, @PathVariable("coursId") Long coursId){
        ManagerCoursId managerCoursId = new ManagerCoursId(managerId, coursId);
        managerCoursService.deleteManagerCours(managerCoursId);

        return ResponseEntity.ok(new ApiResponse("ManagerCours deleted successfully"));
    }
    @GetMapping("/manager_cours/{coursId}/managers")
    public ResponseEntity<List<ManagerInfo>> getManagersByCoursId(@PathVariable Long coursId){
        List<ManagerInfo> managerInfos = managerCoursService.getManagersByCoursId(coursId);

        return ResponseEntity.ok(managerInfos);
    }
    @GetMapping("/manager_cours/{coursId}")
    public ResponseEntity<List<ManagerInfo>> getManagersByCoursIdNotInCours(@PathVariable Long coursId){
        List<ManagerInfo> managerInfos = managerCoursService.getManagersNotInManagerCoursByCoursId(coursId);

        return ResponseEntity.ok(managerInfos);
    }
}
