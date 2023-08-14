package com.cagemini.lifescience.service;

import com.cagemini.lifescience.dao.ApprenantProjetRepository;
import com.cagemini.lifescience.dao.ApprenantRepository;
import com.cagemini.lifescience.dao.ManagerApprenantRepository;
import com.cagemini.lifescience.dao.ProjetRepository;
import com.cagemini.lifescience.entity.*;
import com.cagemini.lifescience.model.ApprenantInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ApprenantProjetServiceImpl implements ApprenantProjetService {
    private final ApprenantProjetRepository apprenantProjetRepository;
    private final ProjetRepository projetRepository;
    private final ApprenantRepository apprenantRepository;
    private final ManagerApprenantRepository managerApprenantRepository;

    @Autowired
    public ApprenantProjetServiceImpl(ApprenantProjetRepository apprenantProjetRepository, ProjetRepository projetRepository, ApprenantRepository apprenantRepository, ManagerApprenantRepository managerApprenantRepository) {
        this.apprenantProjetRepository = apprenantProjetRepository;
        this.projetRepository = projetRepository;
        this.apprenantRepository = apprenantRepository;
        this.managerApprenantRepository = managerApprenantRepository;
    }

    @Override
    public List<ApprenantInfo> getApprenantsByprojetId(Long projetId) {
        List<ApprenantInfo> apprenantInfos = new ArrayList<>();
        List<ApprenantProjet> apprenantProjetsList = apprenantProjetRepository.findAllByProjetId(projetId);
        for (ApprenantProjet apprenantProjet: apprenantProjetsList){
            Apprenant apprenant = apprenantProjet.getApprenant();
            apprenantInfos.add(new ApprenantInfo(apprenant.getId(), apprenant.getFirstName(), apprenant.getLastName()));
        }
        return apprenantInfos;
    }

    @Override
    public void deleteApprenantProjet(ApprenantProjetId apprenantProjetId) {
        apprenantProjetRepository.deleteById(apprenantProjetId);
    }

    @Override
    public void addApprenantProjet(Long apprenantId, Long projetId) {
        ApprenantProjetId apprenantProjetId = new ApprenantProjetId();
        apprenantProjetId.setApprenantId(apprenantId);
        apprenantProjetId.setProjetId(projetId);

        Apprenant apprenant = apprenantRepository.findById(apprenantId).orElse(null);
        Projet projet = projetRepository.findById(projetId).orElse(null);

        if(apprenant != null && projet != null){
            ApprenantProjet apprenantProjet = new ApprenantProjet(apprenant, projet);
            apprenantProjet.setId(apprenantProjetId);

            apprenantProjetRepository.save(apprenantProjet);
        }

    }

    @Override
    public List<ApprenantInfo> getApprenantsByManagerAndNotInProject(Long managerId, Long projetId) {
        List<ApprenantInfo> apprenantInfos = new ArrayList<>();

        List<ManagerApprenant> managerApprenants = managerApprenantRepository.findByManagerId(managerId);
        List<ApprenantProjet> apprenantProjets = apprenantProjetRepository.findAllByProjetId(projetId);

        Set<Long> apprenantIdsInProjet = apprenantProjets.stream()
                .map(apprenantProjet -> apprenantProjet.getId().getApprenantId())
                .collect(Collectors.toSet());
        for (ManagerApprenant managerApprenant : managerApprenants){
            Apprenant apprenant = managerApprenant.getApprenant();
            if(!apprenantIdsInProjet.contains(apprenant.getId())){
                apprenantInfos.add(new ApprenantInfo(apprenant.getId(), apprenant.getFirstName(), apprenant.getLastName()));
            }
        }
        return apprenantInfos;
    }
}
