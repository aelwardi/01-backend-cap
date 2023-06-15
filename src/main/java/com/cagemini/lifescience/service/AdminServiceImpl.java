package com.cagemini.lifescience.service;


import com.cagemini.lifescience.dao.DepartementRepository;
import com.cagemini.lifescience.dao.ManagerRepository;
import com.cagemini.lifescience.entity.Admin;
import com.cagemini.lifescience.dao.AdminRepository;
import com.cagemini.lifescience.entity.Departement;
import com.cagemini.lifescience.entity.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
    private AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository theAdminRepository){

        adminRepository=theAdminRepository;
    }
    @Autowired
    public List<Admin> findAll(){

        return adminRepository.findAll();
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
    public Admin save(Admin theAdmin) {
        return adminRepository.save(theAdmin);
    }

    @Override
    public Admin updateAdmin(Admin theAdmin) {
        return adminRepository.save(theAdmin );
    }

    @Override
    public List<Admin> searchByNameOrLastName(@RequestParam("term") String term) {
        return adminRepository.searchByNameOrLastName(term);
    }


    @Override
    public void deleteById(Long theId) {
        adminRepository.deleteById(theId);

    }

    @Override
    public Departement getDepartementByAdmin(Long adminId) {
        Admin admin = adminRepository.findById(adminId).orElse(null);
        if (admin != null) {
            return admin.getDepartement();
        }
        return null;
    }
    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public Set<Manager> getManagersByAdmin(Long adminId) {
        Admin admin = adminRepository.findById(adminId).orElse(null);
        if (admin != null) {
            return managerRepository.findByAdminId(adminId);
        }
        return null;
    }/*
    @Override
    public Set<Manager> getManagersByAdmin(Long adminId) {
        Admin admin = adminRepository.findById(adminId).orElse(null);
        if (admin != null) {
            return admin.getManagers();
        }
        return null;
    }*/



//    @Override
//    public Page<Admin> findByFirstNameContainingOrLastNameContaining(String lastName, Pageable page) {
//        return adminRepository.findByLastNameContaining(lastName,page);
//    }

}
