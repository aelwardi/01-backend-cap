package com.cagemini.lifescience.service;

import com.cagemini.lifescience.dao.DepartementRepository;
import com.cagemini.lifescience.entity.Departement;
import com.cagemini.lifescience.entity.Manager;
import com.cagemini.lifescience.entity.ManagerCours;
import com.cagemini.lifescience.entity.Projet;
import com.cagemini.lifescience.model.DepartementInfo;
import com.cagemini.lifescience.model.ManagerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DepartementServiceImpl implements DepartementService {

    private final DepartementRepository departementRepository;

    @Autowired
    public DepartementServiceImpl(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }


    @Override
    public List<DepartementInfo> findAll() {
        List<DepartementInfo> departementInfos = new ArrayList<>();
        List<Departement> departementList = departementRepository.findAll();

        for (Departement departement: departementList){
            departementInfos.add(new DepartementInfo(departement.getId(), departement.getName()));
        }
        return departementInfos;
    }

    @Override
    public Departement findById(Long theId) {
        Optional<Departement>result=departementRepository.findById(theId);
        Departement theDepartement =null;
        if (result.isPresent()){
            theDepartement = result.get();
        }
        else {
            throw new RuntimeException("didn't find admin id:"+theId);
        }
        return theDepartement;
    }

    @Override
    public Departement save(Departement theDepartement) {
        return departementRepository.save(theDepartement);
    }

    @Override
    public Departement updateDepartement(Departement theDepartement) {
        return departementRepository.save(theDepartement);
    }

    @Override
    public List<DepartementInfo> findByLastNameContaining(String name) {
        List<DepartementInfo> departementInfos = new ArrayList<>();
        List<Departement> departementList = departementRepository.findByNameContaining(name);

        for (Departement departement: departementList){
            departementInfos.add(new DepartementInfo(departement.getId(), departement.getName()));
        }
        return departementInfos;
    }

    @Override
    public void deleteById(Long theId) {
        departementRepository.deleteById(theId);
    }

    /*
    @Override
    public List<Projet> getProjetsByDepartement(Long departementId) {
        Departement departement = departementRepository.findById(departementId)
                .orElseThrow(() -> new IllegalArgumentException("Department not found with ID: " + departementId));

        return new ArrayList<>(departement.getProjets());
    }*/
}
