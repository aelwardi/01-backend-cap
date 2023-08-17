package com.cagemini.lifescience.service;


import com.cagemini.lifescience.dao.*;
import com.cagemini.lifescience.entity.*;
import com.cagemini.lifescience.model.AdminInfo;
import com.cagemini.lifescience.model.ApprenantInfos;
import com.cagemini.lifescience.model.DepartementInfo;
import com.cagemini.lifescience.model.ManagerInfos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
    private final AdminRepository adminRepository;
    private final SuperAdminRepository superAdminRepository;
    private final DepartementRepository departementRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository theAdminRepository, SuperAdminRepository superAdminRepository, DepartementRepository departementRepository){

        this.adminRepository = theAdminRepository;
        this.superAdminRepository = superAdminRepository;
        this.departementRepository = departementRepository;
    }
    @Autowired
    public List<AdminInfo> findAll(){
        List<AdminInfo> adminInfos = new ArrayList<>();
        List<Admin> adminList = adminRepository.findAll();
        for (Admin admin: adminList){
            Departement departement = admin.getDepartement();
            DepartementInfo departementInfo = new DepartementInfo(departement.getId(), departement.getName());
            adminInfos.add(new AdminInfo(admin.getId(), admin.getLastName(), admin.getFirstName(), admin.getDateBirth(), admin.getPhone(), admin.getSexe(), admin.getEmail(), admin.getPassword(), admin.getEtat(), admin.getPhoto(), admin.getRole(), departementInfo));
        }
        return adminInfos;
    }

    @Override
    public Admin findById(Long theId) {
        Optional<Admin>result=adminRepository.findById(theId);

        Admin theAdmin =null;
        if (result.isPresent()){
            theAdmin = result.get();
        }
        else {
            throw new RuntimeException("didn't find admin id:"+theId);
        }
        return theAdmin;
    }

    @Override
    public AdminInfo getAdminDetails(Long theId) {
        Optional<Admin> result = adminRepository.findById(theId);
        AdminInfo adminInfo = new AdminInfo();
        if (result.isPresent()){
            Admin admin = result.get();
            Departement departement = admin.getDepartement();
            DepartementInfo departementInfo = new DepartementInfo(departement.getId(), departement.getName());
            adminInfo = new AdminInfo(admin.getId(), admin.getLastName(), admin.getFirstName(), admin.getDateBirth(), admin.getPhone(), admin.getSexe(), admin.getEmail(), admin.getPassword(), admin.getEtat(), admin.getPhoto(), admin.getRole(), departementInfo);
        }
        else {
            throw new RuntimeException("didn't find admin id:"+theId);
        }
        return adminInfo;
    }

    @Override
    public Admin save(Long superAdminId, Admin theAdmin) {
        SuperAdmin superAdmin = this.superAdminRepository.findById(superAdminId)
                .orElseThrow(() -> new ResourceNotFoundException("Super-Admin not found with ID: " + superAdminId));
        Departement departement = this.departementRepository.findById(theAdmin.getDepartement().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Departement not found with ID: " + theAdmin.getDepartement().getId()));
        theAdmin.setSuperAdmin(superAdmin);
        return adminRepository.save(theAdmin);
    }

    @Override
    public Admin updateAdmin(Long superAdminId, Long id, Admin theAdmin) {
        Admin admin = this.adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with ID: " + id));
        SuperAdmin superAdmin = this.superAdminRepository.findById(superAdminId)
                .orElseThrow(() -> new ResourceNotFoundException("Super-Admin not found with ID: " + superAdminId));
        admin.setFirstName(theAdmin.getFirstName());
        admin.setLastName(theAdmin.getLastName());
        admin.setDateBirth(theAdmin.getDateBirth());
        admin.setPhone(theAdmin.getPhone());
        admin.setSexe(theAdmin.getSexe());
        admin.setEmail(theAdmin.getEmail());
        admin.setEtat(theAdmin.getEtat());
        admin.setPhoto(theAdmin.getPhoto());
        return adminRepository.save(admin);
    }

    @Override
    public Admin updateProfile(Long id, Admin theAdmin) {
        Admin admin = this.adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with ID: " + id));
        admin.setDateBirth(theAdmin.getDateBirth());
        admin.setPhone(theAdmin.getPhone());
        admin.setPhoto(theAdmin.getPhoto());
        return adminRepository.save(admin);
    }
    @Override
    public List<AdminInfo> searchByNameOrLastName(@RequestParam("term") String term) {
        List<AdminInfo> adminInfos = new ArrayList<>();
        List<Admin> adminList =adminRepository.searchByNameOrLastName(term);
        for (Admin admin: adminList){
            Departement departement = admin.getDepartement();
            DepartementInfo departementInfo = new DepartementInfo(departement.getId(), departement.getName());
            adminInfos.add(new AdminInfo(admin.getId(), admin.getLastName(), admin.getFirstName(), admin.getDateBirth(), admin.getPhone(), admin.getSexe(), admin.getEmail(), admin.getPassword(), admin.getEtat(), admin.getPhoto(), admin.getRole(), departementInfo));
        }
        return adminInfos;
    }


    @Override
    public void deleteById(Long theId) {
        adminRepository.deleteById(theId);

    }
  
    @Override
    public DepartementInfo getDepartementByAdmin(Long adminId) {
        ManagerInfos managerInfos = new ManagerInfos();
        Admin admin = adminRepository.findById(adminId).orElse(null);
        if (admin != null) {
            Departement departement = admin.getDepartement();
            DepartementInfo departementInfo = new DepartementInfo(departement.getId(), departement.getName());
            return  departementInfo;
        }
        else {
            throw new RuntimeException("didn't find admin id:" + adminId);
        }
    }
    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public List<ManagerInfos> getManagersByAdmin(Long adminId) {
        List<ManagerInfos> managerInfos = new ArrayList<>();
        Admin admin = adminRepository.findById(adminId).orElse(null);
        if (admin != null) {
            List<Manager> managerList =  managerRepository.findByAdminId(adminId);

            for (Manager manager: managerList) {
                Departement departement = manager.getDepartement();
                DepartementInfo departementInfo = new DepartementInfo(departement.getId(), departement.getName());

                managerInfos.add(new ManagerInfos(manager.getId(), manager.getLastName(), manager.getFirstName(), manager.getDateBirth(), manager.getPhone(), manager.getSexe(), manager.getEmail(), manager.getPassword(), manager.getEtat(), manager.getPhoto(), manager.getRole(), departementInfo));
            }
        }
        else {
            throw new RuntimeException("didn't find admin id:" + adminId);
        }
        return managerInfos;
    }

  @Autowired
    private ApprenantRepository apprenantRepository;

    @Override
    public List<ApprenantInfos> getApprenantsByAdmin(Long adminId) {
        List<ApprenantInfos> apprenantInfos = new ArrayList<>();
        Admin admin = adminRepository.findById(adminId).orElse(null);
        if (admin != null) {
            List<Apprenant> apprenantList =  apprenantRepository.findByAdminId(adminId);

            for (Apprenant apprenant: apprenantList) {
                Departement departement = apprenant.getDepartement();
                DepartementInfo departementInfo = new DepartementInfo(departement.getId(), departement.getName());

                apprenantInfos.add(new ApprenantInfos(apprenant.getId(), apprenant.getLastName(), apprenant.getFirstName(), apprenant.getDateBirth(), apprenant.getPhone(), apprenant.getSexe(), apprenant.getEmail(), apprenant.getPassword(), apprenant.getEtat(), apprenant.getPhoto(), apprenant.getRole(), departementInfo));
            }
        }
        else {
            throw new RuntimeException("didn't find admin id:" + adminId);
        }
        return apprenantInfos;
    }


}
