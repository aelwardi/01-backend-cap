package com.cagemini.lifescience.service;

import com.cagemini.lifescience.entity.Admin;
import com.cagemini.lifescience.entity.Apprenant;
import com.cagemini.lifescience.entity.Departement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    Set<Apprenant> getApprenantsByAdmin(Long adminId);

}
