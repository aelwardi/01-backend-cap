package com.cagemini.lifescience.service;

import com.cagemini.lifescience.entity.Admin;
import com.cagemini.lifescience.entity.Departement;
import com.cagemini.lifescience.entity.Manager;
import com.cagemini.lifescience.entity.Apprenant;

import java.util.List;
import java.util.Set;


public interface AdminService {
    List<Admin>findAll();
    Admin findById(Long theId);
    Admin save(Admin theAdmin);
    Admin updateAdmin(Admin theAdmin);
    List<Admin> searchByNameOrLastName(String term);
    void  deleteById(Long theId);
    Departement getDepartementByAdmin(Long adminId);
    Set<Manager> getManagersByAdmin(Long adminId);
    Set<Apprenant> getApprenantsByAdmin(Long adminId);

}
