package com.cagemini.lifescience.service;

import com.cagemini.lifescience.dao.AdminRepository;
import com.cagemini.lifescience.dao.ApprenantRepository;
import com.cagemini.lifescience.dao.ManagerApprenantRepository;
import com.cagemini.lifescience.dao.ManagerRepository;
import com.cagemini.lifescience.entity.*;
import com.cagemini.lifescience.model.ManagerApprenantDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void addManagerApprenant(Long apprenantId, Long managerId) {
        ManagerApprenantId managerApprenantId  = new ManagerApprenantId();
        managerApprenantId.setApprenantId(apprenantId);
        managerApprenantId.setManagerId(managerId);

        Apprenant apprenant = apprenantRepository.findById(apprenantId).orElse(null);
        Manager manager = managerRepository.findById(managerId).orElse(null);

        if (apprenant != null && manager != null) {
            ManagerApprenant managerApprenant = new ManagerApprenant(apprenant, manager);
            managerApprenant.setId(managerApprenantId);

            managerApprenantRepository.save(managerApprenant);
        }
    }

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

    @Override
    public void deleteManagerApprenant(ManagerApprenantId id) {
        managerApprenantRepository.deleteById(id);
    }

    /*
    @Override
    public List<Apprenant> getApprenantsByManagerId(Long managerId) {
        Optional<Manager> managerOptional = managerRepository.findById(managerId);
        if (managerOptional.isPresent()) {
            Manager manager = managerOptional.get();
            List<ManagerApprenant> managerApprenants = managerApprenantRepository.findByManager(manager);
            return managerApprenants.stream()
                    .map(ManagerApprenant::getApprenant)
                    .collect(Collectors.toList());
        } else {
            throw new EntityNotFoundException("Manager not found with ID: " + managerId);
        }
    }*/
}
