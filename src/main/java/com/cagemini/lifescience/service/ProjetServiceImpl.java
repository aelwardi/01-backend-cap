package com.cagemini.lifescience.service;

import com.cagemini.lifescience.dao.ManagerRepository;
import com.cagemini.lifescience.dao.ProjetRepository;
import com.cagemini.lifescience.entity.Cours;
import com.cagemini.lifescience.entity.Departement;
import com.cagemini.lifescience.entity.Manager;
import com.cagemini.lifescience.entity.Projet;
import com.cagemini.lifescience.model.CoursInfo;
import com.cagemini.lifescience.model.ProjetDTO;
import com.cagemini.lifescience.model.ProjetInfo;
import com.cagemini.lifescience.model.ProjetInfos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProjetServiceImpl implements ProjetService {

    private final ProjetRepository projetRepository;
    private final ManagerRepository managerRepository;

    @Autowired
    public ProjetServiceImpl(ProjetRepository projetRepository, ManagerRepository managerRepository) {
        this.projetRepository = projetRepository;
        this.managerRepository = managerRepository;
    }


    @Override
    public Projet save(Long managerId, Projet theProjet) {
        Optional<Manager> managers = managerRepository.findById(managerId);

        if (managers.isPresent()) {
            Manager manager = managers.get();
            theProjet.setDepartement(manager.getDepartement());
        }else {

        }
        return projetRepository.save(theProjet);
    }

    @Override
    public Projet updateProjet(Long id, Projet theProjet) {
        Projet projet = projetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + id));
        projet.setPhoto(theProjet.getPhoto());
        projet.setName(theProjet.getName());
        projet.setNameClient(theProjet.getNameClient());
        projet.setDescription(theProjet.getDescription());
        return projetRepository.save(projet );
    }

    @Override
    public void deleteById(Long theId) {
        projetRepository.deleteById(theId);
    }

    @Override
    public Projet findById(Long theId) {
        Optional<Projet> result = projetRepository.findById(theId);
        Projet theProjet = null;
        if (result.isPresent()){
            theProjet = result.get();
        }
        else {
            throw new RuntimeException("didn't find project id:"+theId);
        }
        return theProjet;
    }

    @Override
    public List<ProjetInfos> searchByNameOrNameClientAndDepartementId(String term, Long managerId) {
        List<ProjetInfos> projetInfos = new ArrayList<>();
        Optional<Manager> managers = managerRepository.findById(managerId);

        if (managers.isPresent()){
            Manager manager = managers.get();
            List<Projet> projetList = projetRepository.searchByNameOrNameClientAndDepartementId(term, manager.getDepartement().getId());
            for (Projet projet: projetList) {
                projetInfos.add(new ProjetInfos(projet.getId(), projet.getName(), projet.getNameClient(), projet.getDescription(), projet.getPhoto()));
            }
        }
        else {

        }
        return projetInfos;
    }

    @Override
    public List<ProjetDTO> getProjetsAndCoursByManagerId(Long managerId) {
        Manager manager = managerRepository.findById(managerId).orElse(null);

        if (manager == null) {
            // Gérer le cas où le manager n'est pas trouvé
            return Collections.emptyList();
        }

        Departement departement = manager.getDepartement();
        List<ProjetDTO> projetDTOs = new ArrayList<>();

        for (Projet projet : departement.getProjets()) {
            ProjetInfo projetInfo = new ProjetInfo(projet.getId(), projet.getName());
            List<CoursInfo> coursInfos = new ArrayList<>();
            for (Cours cours : projet.getCourses()) {
                if (cours.getManager().getId().equals(managerId)) {
                    CoursInfo coursInfo = new CoursInfo();
                    coursInfo.setId(cours.getId());
                    coursInfo.setTitle(cours.getTitle());
                    coursInfos.add(new CoursInfo(cours.getId(), cours.getTitle(), cours.getDescription(), cours.getActor(), cours.getDateMAJ(), cours.getPhoto()));
                }
            }
            projetDTOs.add(new ProjetDTO(projetInfo, coursInfos));
        }
        return projetDTOs;
        /*
        List<Projet> projets = departement.getProjets();

        List<ProjetDTO> projetDTOs = new ArrayList<>();
        for (Projet projet : projets) {
            ProjetInfo projetInfo = new ProjetInfo(projet.getId(), projet.getName());

            List<CoursInfo> coursInfos = projet.getCourses().stream()
                    .map(cours -> new CoursInfo(cours.getId(), cours.getTitle(), cours.getDescription(), cours.getActor(), cours.getDateMAJ()))
                    .collect(Collectors.toList());

            ProjetDTO projetDTO = new ProjetDTO(projetInfo, coursInfos);
            projetDTOs.add(projetDTO);
        }
        return projetDTOs;*/
    }

    @Override
    public List<ProjetInfos> getProjetsByDepartement(Long managerId) {
        List<ProjetInfos> projetInfos = new ArrayList<>();
        Optional<Manager> managers = managerRepository.findById(managerId);

        if (managers.isPresent()){
            Manager manager = managers.get();
            List<Projet> projetList = manager.getDepartement().getProjets();
            for (Projet projet: projetList) {
                projetInfos.add(new ProjetInfos(projet.getId(), projet.getName(), projet.getNameClient(), projet.getDescription(), projet.getPhoto()));
            }
        }
        else {

        }
        return projetInfos;
    }

}
