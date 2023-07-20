package com.cagemini.lifescience.rest;

import com.cagemini.lifescience.entity.Departement;
import com.cagemini.lifescience.entity.Role;
import com.cagemini.lifescience.entity.SuperAdmin;
import com.cagemini.lifescience.service.SuperAdminService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;


@RestController
@CrossOrigin("http://localhost:4200/")
public class SuperAdminController {

    private SuperAdminService superAdminService;

    public SuperAdminController(SuperAdminService superAdminService) {
        this.superAdminService = superAdminService;
    }


    @GetMapping("/super_admin/{superAdminId}")
    public SuperAdmin findById(@PathVariable Long superAdminId){
        SuperAdmin theSuperAdmin = superAdminService.findById(superAdminId);
        if (theSuperAdmin == null){
            throw new RuntimeException("the super admin id not found "+superAdminId);
        }
        else
            return theSuperAdmin;
    }


    @PostMapping("/super_admin" )
    public String addSuperAdmin(
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("dateOfBirth") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth,
            @RequestParam("sexe") String sexe,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("etat") Boolean etat,
            @RequestParam("phone") String phone,
            @RequestParam("role") String role

            ) throws IOException {
        return superAdminService.save(photo, firstName, lastName, dateOfBirth, sexe, email, password, etat, phone, role);
    }

    /*
    @PostMapping("/super_admin" )
    public SuperAdmin addDepartement(@RequestBody SuperAdmin theSuperAdmin){
        theSuperAdmin.setId(0L);
        SuperAdmin dbSuperAdmin = superAdminService.save(theSuperAdmin);
        return dbSuperAdmin;
    }
    */

    /*
    @PutMapping("/super_admin/{id}")
    public String updateSuperAdmin(
            @PathVariable Long id,
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("dateOfBirth") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth,
            @RequestParam("sexe") String sexe,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("etat") Boolean etat,
            @RequestParam("phone") String phone,
            @RequestParam("role") String role
    ) throws IOException {
        // Recherche de l'administrateur super existant par ID
        SuperAdmin existingSuperAdmin = superAdminService.findById(id);

        if (existingSuperAdmin == null) {
            throw new RuntimeException("Super admin not found with ID: " + id);
        }

        // Update super admin
        existingSuperAdmin.setFirstName(firstName);
        existingSuperAdmin.setLastName(lastName);
        existingSuperAdmin.setDateOfBirth(dateOfBirth);
        existingSuperAdmin.setSexe(sexe);
        existingSuperAdmin.setEmail(email);
        existingSuperAdmin.setPassword(password);
        existingSuperAdmin.setEtat(etat);
        existingSuperAdmin.setPhone(phone);
        existingSuperAdmin.setRole(Role.valueOf(role));
        existingSuperAdmin.setPhoto(photo.getBytes());

        // Save the update
        superAdminService.updateSuperAdmin(existingSuperAdmin);

        return "Super admin updated successfully";
    }

     */

    @PutMapping("/super_admin/{id}")
    public SuperAdmin updateSuperAdmin(@PathVariable Long id,@RequestBody SuperAdmin theSuperAdmin) throws IOException {
        theSuperAdmin.setId(id);
        return superAdminService.updateSuperAdmin(theSuperAdmin);
    }

/*
    @DeleteMapping("/super_admin/{adminId}")
    public  String deleteSuperAdmin(@PathVariable Long superAdminId){
        SuperAdmin theSuperAdmin = superAdminService.findById(superAdminId);

        //throw exception if null

        if (theSuperAdmin == null){
            throw new RuntimeException("the super admin id not found "+superAdminId);
        }
        superAdminService.deleteById(superAdminId);
        return ("Deleted super admin id :" + superAdminId);
    }*/
}
