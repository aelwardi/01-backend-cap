package com.cagemini.lifescience.service;

import com.cagemini.lifescience.entity.Admin;
import com.cagemini.lifescience.entity.Departement;
import com.cagemini.lifescience.entity.Manager;
import com.cagemini.lifescience.entity.Apprenant;
import com.cagemini.lifescience.model.AdminInfo;
import com.cagemini.lifescience.model.ApprenantInfos;
import com.cagemini.lifescience.model.DepartementInfo;
import com.cagemini.lifescience.model.ManagerInfos;

import java.util.List;
import java.util.Set;


public interface AdminService {
    List<AdminInfo>findAll();
    Admin findById(Long theId);
    AdminInfo getAdminDetails(Long theId);
    Admin save(Long superAdminId, Admin theAdmin);
    Admin updateAdmin(Long superAdminId, Long id, Admin theAdmin);
    Admin updateProfile(Long id, Admin theAdmin);
    List<AdminInfo> searchByNameOrLastName(String term);
    void  deleteById(Long theId);
    DepartementInfo getDepartementByAdmin(Long adminId);
    List<ManagerInfos> getManagersByAdmin(Long adminId);
    List<ApprenantInfos> getApprenantsByAdmin(Long adminId);

}
