package com.cagemini.lifescience.service;

import com.cagemini.lifescience.dao.ApprenantRepository;
import com.cagemini.lifescience.dao.ManagerApprenantRepository;
import com.cagemini.lifescience.dao.ManagerRepository;
import com.cagemini.lifescience.entity.Apprenant;
import com.cagemini.lifescience.entity.Manager;
import com.cagemini.lifescience.entity.ManagerApprenant;
import com.cagemini.lifescience.entity.ManagerApprenantId;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ManagerApprenantServiceImpl implements ManagerApprenantService{

    private ManagerApprenantRepository managerApprenantRepository;
    private ApprenantRepository apprenantRepository;
    private ManagerRepository managerRepository;

    @Autowired
    public ManagerApprenantServiceImpl(ManagerApprenantRepository managerApprenantRepository, ApprenantRepository apprenantRepository, ManagerRepository managerRepository) {
        this.managerApprenantRepository = managerApprenantRepository;
        this.apprenantRepository = apprenantRepository;
        this.managerRepository = managerRepository;
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
    }
}
