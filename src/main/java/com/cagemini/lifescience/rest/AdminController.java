package com.cagemini.lifescience.rest;

import com.cagemini.lifescience.entity.Admin;

import com.cagemini.lifescience.entity.Apprenant;
import com.cagemini.lifescience.entity.Departement;


import com.cagemini.lifescience.entity.Manager;
import com.cagemini.lifescience.service.AdminService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
public class AdminController {

    private AdminService adminService;


    //quick and dirty :inject Admin service

    public AdminController (AdminService theadminService){
        adminService=theadminService;
    }




    @GetMapping("/admins/{adminId}/managers")
    public Set<Manager> getManagersByAdminId(@PathVariable Long adminId) {
        return adminService.getManagersByAdmin(adminId);
    }
    //export "/Admins"and return a list of Admins
    @GetMapping("/admins")
    public List<Admin> findAll (){
        return adminService.findAll();
    }
    @GetMapping("/admins/{adminId}/departements")
    public Departement getDepartementByAdmin(@PathVariable Long adminId) {
        return adminService.getDepartementByAdmin(adminId);
    }
    @GetMapping("/admins/{adminId}/apprenants")
    public Set<Apprenant> getApprenantsByAdminId(@PathVariable Long adminId) {
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
    public  Admin addAdmin(@RequestBody Admin theAdmin){
        theAdmin.setId(0L);
        Admin dbAdmin = adminService.save(theAdmin);
        return dbAdmin;
    }

//    @PutMapping("/admins/{adminId}")
//    public Admin updateAdmin(@RequestBody Admin theAdmin){
//        Admin dbAdmin = adminService.save(theAdmin);
//        return dbAdmin;
//
//    }

    @PutMapping("/admins/{id}")
    public Admin updateAdmin(@PathVariable Long id, @RequestBody Admin theAdmin) {
        theAdmin.setId(id); // Set the ID received from the path to the Admin object
        return adminService.updateAdmin(theAdmin);
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
    public List<Admin> searchAdmins(
            @RequestParam("term") String term) {
        return adminService.searchByNameOrLastName(term);
    }

    @GetMapping("/admins/details")
    public ResponseEntity<Admin> getAdminDetails(
            @RequestParam("theId") Long theId) {
        Admin admin = adminService.findById(theId);
        if (admin == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(admin);
    }
}
