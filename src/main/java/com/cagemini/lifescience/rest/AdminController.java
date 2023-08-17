package com.cagemini.lifescience.rest;

import com.cagemini.lifescience.entity.Admin;

import com.cagemini.lifescience.entity.Apprenant;
import com.cagemini.lifescience.entity.Departement;


import com.cagemini.lifescience.entity.Manager;
import com.cagemini.lifescience.model.*;
import com.cagemini.lifescience.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
public class AdminController {

    private AdminService adminService;


    @Autowired
    public AdminController (AdminService theadminService){
        adminService=theadminService;
    }


    @GetMapping("/admins/{adminId}/managers")
    public List<ManagerInfos> getManagersByAdminId(@PathVariable Long adminId) {
        return adminService.getManagersByAdmin(adminId);
    }
    //export "/Admins"and return a list of Admins
    @GetMapping("/admins")
    public List<AdminInfo> findAll (){
        return adminService.findAll();
    }
    @GetMapping("/admins/{adminId}/departements")
    public DepartementInfo getDepartementByAdmin(@PathVariable Long adminId) {
        return adminService.getDepartementByAdmin(adminId);
    }
    @GetMapping("/admins/{adminId}/apprenants")
    public List<ApprenantInfos> getApprenantsByAdminId(@PathVariable Long adminId) {
        return adminService.getApprenantsByAdmin(adminId);
    }

    @GetMapping("/admins/{adminId}")
    public Admin findById(@PathVariable Long adminId){
        Admin theAdmin = adminService.findById(adminId);
        if (theAdmin == null){
            throw new RuntimeException("the Admin id not found "+adminId);
        }
        else
        return theAdmin;
    }

    @PostMapping("/admins" )
    public ApiResponse addAdmin(@RequestParam("superAdminId") Long superAdminId, @RequestBody Admin theAdmin){
        theAdmin.setId(0L);
        Admin dbAdmin = adminService.save(superAdminId, theAdmin);
        return new ApiResponse("admin added");
    }

    @PutMapping("/admins/{id}")
    public ApiResponse updateAdmin(@RequestParam("superAdminId") Long superAdminId,@PathVariable Long id, @RequestBody Admin theAdmin) {
        Admin dbAdmin = adminService.updateAdmin(superAdminId, id, theAdmin);
        return new ApiResponse("admin updated");
    }
    @PutMapping("/admins/profile/{id}")
    public ApiResponse updateProfile(@PathVariable Long id, @RequestBody Admin theAdmin) {
        adminService.updateProfile(id, theAdmin);
        return new ApiResponse("admin profile updated");
    }


    @DeleteMapping("/admins/{adminId}")
    public  String deleteAdmin(@PathVariable Long adminId){
        Admin theAdmin = adminService.findById(adminId);
        //throw exception if null

        if (theAdmin == null){
            throw new RuntimeException("the Admin id not found "+adminId);
        }
        adminService.deleteById(adminId);
        return ("Deleted admin id :" + adminId);


    }
    @GetMapping("/admins/search")
    public List<AdminInfo> searchAdmins(
            @RequestParam("term") String term) {
        return adminService.searchByNameOrLastName(term);
    }

    @GetMapping("/admins/details")
    public ResponseEntity<AdminInfo> getAdminDetails(
            @RequestParam("theId") Long theId) {
        AdminInfo admin = this.adminService.getAdminDetails(theId);
        if (admin == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(admin);
    }
}
