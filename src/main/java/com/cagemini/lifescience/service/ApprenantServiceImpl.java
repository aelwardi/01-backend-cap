package com.cagemini.lifescience.service;

import com.cagemini.lifescience.dao.AdminRepository;
import com.cagemini.lifescience.dao.ApprenantRepository;

import com.cagemini.lifescience.dao.DepartementRepository;
import com.cagemini.lifescience.entity.Admin;
import com.cagemini.lifescience.entity.Apprenant;
import com.cagemini.lifescience.entity.Departement;
import com.cagemini.lifescience.entity.Manager;
import com.cagemini.lifescience.model.AdminInfo;
import com.cagemini.lifescience.model.ApprenantInfos;
import com.cagemini.lifescience.model.DepartementInfo;
import com.cagemini.lifescience.model.ManagerInfos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service

public class ApprenantServiceImpl implements ApprenantService {
    private final ApprenantRepository apprenantRepository;
    private final DepartementRepository departementRepository;
    private final AdminRepository adminRepository;

    @Autowired
    public ApprenantServiceImpl(ApprenantRepository theApprenantRepository, DepartementRepository departementRepository, AdminRepository adminRepository){
        this.apprenantRepository = theApprenantRepository;
        this.departementRepository = departementRepository;
        this.adminRepository = adminRepository;

    }
    @Autowired
    public List<Apprenant> findAll(){

        return apprenantRepository.findAll();
    }
    @Override
    public Apprenant findById(Long theId) {
        Optional<Apprenant> result=apprenantRepository.findById(theId);

        Apprenant theApprenant = null;
        if (result.isPresent()){
            theApprenant = result.get();
        }
        else {
            throw new RuntimeException("didn't find apprenant id:"+theId);
        }
        return theApprenant;
    }

    @Override
    public Apprenant save(Long adminId, Apprenant theApprenanat) {
        Admin admin = this.adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with ID: " + adminId));
        Departement departement = this.departementRepository.findById(theApprenanat.getDepartement().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Departement not found with ID: " + theApprenanat.getDepartement().getId()));
        theApprenanat.setAdmin(admin);
        return apprenantRepository.save(theApprenanat);
    }

    @Override
    public Apprenant updateApprenant(Long adminId, Long id, Apprenant theApprenanat) {
        Apprenant apprenant = this.apprenantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Apprenant not found with ID: " + id));
        Admin admin = this.adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with ID: " + adminId));
        apprenant.setFirstName(theApprenanat.getFirstName());
        apprenant.setLastName(theApprenanat.getLastName());
        apprenant.setDateBirth(theApprenanat.getDateBirth());
        apprenant.setPhone(theApprenanat.getPhone());
        apprenant.setSexe(theApprenanat.getSexe());
        apprenant.setEmail(theApprenanat.getEmail());
        apprenant.setEtat(theApprenanat.getEtat());
        apprenant.setPhoto(theApprenanat.getPhoto());
        return apprenantRepository.save(apprenant);
    }

    @Override
    public List<ApprenantInfos> searchByNameOrLastName(@RequestParam("adminId") Long adminId, @RequestParam("term") String term) {
        List<ApprenantInfos> apprenantInfos = new ArrayList<>();
        Admin admin = adminRepository.findById(adminId).orElse(null);
        if (admin != null) {
            List<Apprenant> apprenantList =  apprenantRepository.searchByNameOrLastName(adminId ,term);

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

    @Override
    public void deleteById(Long theId) {
        apprenantRepository.deleteById(theId);

    }

    @Override
    public ApprenantInfos getApprenantByAdminIdAndApprenantId(Long adminId, Long apprenantId) {
        Admin admin = this.adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with ID: " + adminId));
        Apprenant apprenant = this.apprenantRepository.findById(apprenantId)
                .orElseThrow(() -> new ResourceNotFoundException("Apprenant not found with ID: " + apprenantId));
        Departement departement = apprenant.getDepartement();
        DepartementInfo departementInfo = new DepartementInfo(departement.getId(), departement.getName());
        ApprenantInfos apprenantInfos = new ApprenantInfos(apprenant.getId(), apprenant.getLastName(), apprenant.getFirstName(), apprenant.getDateBirth(), apprenant.getPhone(), apprenant.getSexe(), apprenant.getEmail(), apprenant.getPassword(), apprenant.getEtat(), apprenant.getPhoto(), apprenant.getRole(), departementInfo);
        return apprenantInfos;
    }

    @Override
    public ApprenantInfos getApprenantDetails(Long theId) {
        Optional<Apprenant> result = apprenantRepository.findById(theId);
        ApprenantInfos apprenantInfos = new ApprenantInfos();
        if (result.isPresent()){
            Apprenant apprenant = result.get();
            Departement departement = apprenant.getDepartement();
            DepartementInfo departementInfo = new DepartementInfo(departement.getId(), departement.getName());
            apprenantInfos = new ApprenantInfos(apprenant.getId(), apprenant.getLastName(), apprenant.getFirstName(), apprenant.getDateBirth(), apprenant.getPhone(), apprenant.getSexe(), apprenant.getEmail(), apprenant.getPassword(), apprenant.getEtat(), apprenant.getPhoto(), apprenant.getRole(), departementInfo);
        }
        else {
            throw new RuntimeException("didn't find apprenant id:"+theId);
        }
        return apprenantInfos;
    }

    @Override
    public Apprenant updateProfile(Long id, Apprenant theApprenant) {
        Apprenant apprenant = this.apprenantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultant not found with ID: " + id));
        apprenant.setDateBirth(theApprenant.getDateBirth());
        apprenant.setPhone(theApprenant.getPhone());
        apprenant.setPhoto(theApprenant.getPhoto());
        return apprenantRepository.save(apprenant);
    }


}
