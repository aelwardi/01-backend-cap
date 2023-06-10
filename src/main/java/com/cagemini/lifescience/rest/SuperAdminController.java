package com.cagemini.lifescience.rest;

import com.cagemini.lifescience.entity.Admin;
import com.cagemini.lifescience.entity.Departement;
import com.cagemini.lifescience.entity.SuperAdmin;
import com.cagemini.lifescience.service.SuperAdminService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
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
    public SuperAdmin addDepartement(@RequestBody SuperAdmin theSuperAdmin){
        theSuperAdmin.setId(0L);
        SuperAdmin dbSuperAdmin = superAdminService.save(theSuperAdmin);
        return dbSuperAdmin;
    }

    @PutMapping("/super_admin/{id}")
    public SuperAdmin updateSuperAdmin(@PathVariable Long id,@RequestBody SuperAdmin theSuperAdmin){
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
