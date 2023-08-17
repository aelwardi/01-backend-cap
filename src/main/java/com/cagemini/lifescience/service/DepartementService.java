package com.cagemini.lifescience.service;

import com.cagemini.lifescience.entity.Departement;
import com.cagemini.lifescience.entity.Projet;
import com.cagemini.lifescience.model.DepartementInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartementService {
    List<DepartementInfo> findAll();
    Departement findById(Long theId);
    Departement save(Departement theDepartement);
    Departement updateDepartement(Departement theDepartement);
    List<DepartementInfo> findByLastNameContaining(String name);
    void  deleteById(Long theId);
/*
    List<Projet> getProjetsByDepartement(Long departementId);*/
}
