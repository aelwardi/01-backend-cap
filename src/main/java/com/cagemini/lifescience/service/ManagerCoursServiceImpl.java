package com.cagemini.lifescience.service;


import com.cagemini.lifescience.dao.CoursRepository;
import com.cagemini.lifescience.dao.DepartementRepository;
import com.cagemini.lifescience.dao.ManagerCoursRepository;
import com.cagemini.lifescience.dao.ManagerRepository;
import com.cagemini.lifescience.entity.Cours;
import com.cagemini.lifescience.entity.Manager;
import com.cagemini.lifescience.entity.ManagerCours;
import com.cagemini.lifescience.entity.ManagerCoursId;
import com.cagemini.lifescience.model.ManagerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ManagerCoursServiceImpl implements ManagerCoursService {
    private final ManagerCoursRepository managerCoursRepository;
    private final ManagerRepository managerRepository;
    private final CoursRepository coursRepository;
    private final DepartementRepository departementRepository;

    @Autowired
    public ManagerCoursServiceImpl(ManagerCoursRepository managerCoursRepository, ManagerRepository managerRepository, CoursRepository coursRepository, DepartementRepository departementRepository) {
        this.managerCoursRepository = managerCoursRepository;
        this.managerRepository = managerRepository;
        this.coursRepository = coursRepository;
        this.departementRepository = departementRepository;
    }

    @Override
    public void addManagerCours(Long managerId, Long coursId) {
        ManagerCoursId managerCoursId = new ManagerCoursId();
        managerCoursId.setManagerId(managerId);
        managerCoursId.setCoursId(coursId);

        Manager manager = managerRepository.findById(managerId).orElse(null);
        Cours cours = coursRepository.findById(coursId).orElse(null);

        if(manager != null && cours != null){
            ManagerCours managerCours = new ManagerCours(manager,cours);
            managerCours.setId(managerCoursId);

            managerCoursRepository.save(managerCours);
        }
    }

    @Override
    public void deleteManagerCours(ManagerCoursId managerCoursId) {
        managerCoursRepository.deleteById(managerCoursId);
    }

    @Override
    public List<ManagerInfo> getManagersByCoursId(Long coursId) {
        List<ManagerInfo> managerInfos = new ArrayList<>();
        List<ManagerCours> managerCoursList = managerCoursRepository.findAllByCoursId(coursId);

        for (ManagerCours managerCours: managerCoursList){
            Manager manager = managerCours.getManager();
            managerInfos.add(new ManagerInfo(manager.getId(), manager.getLastName(), manager.getFirstName()));
        }

        return managerInfos;
    }

    @Override
    public List<ManagerInfo> getManagersNotInManagerCoursByCoursId(Long coursId) {
        Optional<Cours> coursOptional = coursRepository.findById(coursId);
        if(coursOptional.isPresent()){
            Cours cours = coursOptional.get();
            Long departementId = cours.getProjet().getDepartement().getId();
            List<ManagerInfo> managerInfos = new ArrayList<>();
            List<Manager> managers = managerRepository.findManagersNotInManagerCoursByCoursIdAndDepartementId(coursId, departementId);
            for (Manager manager: managers){
                managerInfos.add(new ManagerInfo(manager.getId(), manager.getLastName(), manager.getFirstName()));
            }
            return managerInfos;
        }else {
            throw new IllegalArgumentException("Cours with ID " + coursId + " not found.");
        }
    }
}
