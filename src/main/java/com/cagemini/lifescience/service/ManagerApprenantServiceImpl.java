package com.cagemini.lifescience.service;

import com.cagemini.lifescience.dao.AdminRepository;
import com.cagemini.lifescience.dao.ApprenantRepository;
import com.cagemini.lifescience.dao.ManagerApprenantRepository;
import com.cagemini.lifescience.dao.ManagerRepository;
import com.cagemini.lifescience.entity.*;
import com.cagemini.lifescience.model.ApprenantInfos;
import com.cagemini.lifescience.model.DepartementInfo;
import com.cagemini.lifescience.model.ManagerApprenantDTO;
import com.cagemini.lifescience.model.ManagerInfos;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ManagerApprenantServiceImpl implements ManagerApprenantService{

    private ManagerApprenantRepository managerApprenantRepository;
    private ApprenantRepository apprenantRepository;
    private ManagerRepository managerRepository;
    private AdminRepository adminRepository;

    @Autowired
    public ManagerApprenantServiceImpl(ManagerApprenantRepository managerApprenantRepository, ApprenantRepository apprenantRepository, ManagerRepository managerRepository, AdminRepository adminRepository) {
        this.managerApprenantRepository = managerApprenantRepository;
        this.apprenantRepository = apprenantRepository;
        this.managerRepository = managerRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public List<ManagerApprenantDTO> getManagerApprenant(Long adminId) {
        List<ManagerApprenantDTO> managerApprenantDTOS = new ArrayList<>();
        Admin admin = this.adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with ID: " + adminId));
        List<Manager> managerList = admin.getManagers();
        for (Manager manager: managerList){
            Departement departement = manager.getDepartement();
            DepartementInfo departementInfo = new DepartementInfo(departement.getId(), departement.getName());
            ManagerInfos managerInfos = new ManagerInfos(manager.getId(), manager.getLastName(), manager.getFirstName(), manager.getDateBirth(), manager.getPhone(), manager.getSexe(), manager.getEmail(), manager.getPassword(), manager.getEtat(), manager.getPhoto(), manager.getRole(), departementInfo);
            List<ApprenantInfos> apprenantInfos = new ArrayList<>();
            List<Apprenant> apprenantList = manager.getApprenants();
            for(Apprenant apprenant: apprenantList){
                apprenantInfos.add(new ApprenantInfos(apprenant.getId(), apprenant.getLastName(), apprenant.getFirstName(), apprenant.getDateBirth(), apprenant.getPhone(), apprenant.getSexe(), apprenant.getEmail(), apprenant.getPassword(), apprenant.getEtat(), apprenant.getPhoto(), apprenant.getRole(), departementInfo));
            }
            managerApprenantDTOS.add(new ManagerApprenantDTO(managerInfos, apprenantInfos));
        }
        return managerApprenantDTOS;
    }
    @Override
    public void deleteManagerApprenant(ManagerApprenantId id) {
        managerApprenantRepository.deleteById(id);
    }

    @Override
    public List<ApprenantInfos> getManagerApprenantNoAssigned(Long adminId, Long managerId) {
        List<ApprenantInfos> apprenantInfos = new ArrayList<>();
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with ID: " + adminId));
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new ResourceNotFoundException("Manager not found with ID: " + managerId));

        // Get list apprenant by admin
        List<Apprenant> apprenants = admin.getApprenants();

        // Get the list of apprenant not assign with manager
        apprenants = apprenants.stream()
                .filter(apprenant -> !manager.getApprenants().contains(apprenant))
                .collect(Collectors.toList());
        for (Apprenant apprenant: apprenants){
            Departement departement = apprenant.getDepartement();
            DepartementInfo departementInfo = new DepartementInfo(departement.getId(), departement.getName());

            apprenantInfos.add(new ApprenantInfos(apprenant.getId(), apprenant.getLastName(), apprenant.getFirstName(), apprenant.getDateBirth(), apprenant.getPhone(), apprenant.getSexe(), apprenant.getEmail(), apprenant.getPassword(), apprenant.getEtat(), apprenant.getPhoto(), apprenant.getRole(), departementInfo));
        }
       // return apprenants;
        return apprenantInfos;
    }



    @Override
    public void addManagerApprenant(Long apprenantId, Long managerId) {
        ManagerApprenantId managerApprenantId  = new ManagerApprenantId();
        managerApprenantId.setApprenantId(apprenantId);
        managerApprenantId.setManagerId(managerId);

        Apprenant apprenant = apprenantRepository.findById(apprenantId)
                .orElseThrow(() -> new ResourceNotFoundException("Apprenant not found with ID: " + apprenantId));
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new ResourceNotFoundException("Manager not found with ID: " + managerId));

        ManagerApprenant managerApprenant = new ManagerApprenant(apprenant, manager);
        managerApprenant.setId(managerApprenantId);

        managerApprenantRepository.save(managerApprenant);
    }
/*
    @Override
    public ManagerApprenantDTO getApprenantsByManagerId(Long managerId) {
        Optional<Manager> managerOptional = managerRepository.findById(managerId);
        if (managerOptional.isPresent()){
            Manager manager = managerOptional.get();
            List<ManagerApprenant> managerApprenants = managerApprenantRepository.findByManager(manager);
            List<Apprenant> apprenants = managerApprenants.stream()
                    .map(ManagerApprenant::getApprenant)
                    .collect(Collectors.toList());

            ManagerApprenantDTO managerApprenantDTO = new ManagerApprenantDTO();
            managerApprenantDTO.setManager(manager);
            //managerApprenantDTO.setApprenants(apprenants);
            return managerApprenantDTO;
        } else {
            throw new EntityNotFoundException("Manager not found with ID: " + managerId);
        }
    }

    @Override
    public List<Apprenant> getApprenantsByAdminAndManager(Long adminId, Long managerId) {
        // Get admin by ID
        Optional<Admin> adminOptional = adminRepository.findById(adminId);
        if (adminOptional.isEmpty()) {
            // Admin not found
            return Collections.emptyList();
        }
        // Get manager by ID
        Optional<Manager> managerOptional = managerRepository.findById(managerId);
        if (managerOptional.isEmpty()) {
            // Manager not found
            return Collections.emptyList();
        }

        Admin admin = adminOptional.get();
        Manager manager = managerOptional.get();

        // Get list apprenant by admin
        List<Apprenant> apprenants = admin.getApprenants();

        // Get the list of apprenant not assign with manager
        apprenants = apprenants.stream()
                .filter(apprenant -> !manager.getApprenants().contains(apprenant))
                .collect(Collectors.toList());

        return apprenants;
    }

*/
}
