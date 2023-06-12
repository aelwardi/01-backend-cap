package com.cagemini.lifescience.service;

import com.cagemini.lifescience.entity.Admin;
import com.cagemini.lifescience.entity.Departement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartementService {
    List<Departement> findAll();
    Departement findById(Long theId);
    Departement save(Departement theDepartement);
    Departement updateDepartement(Departement theDepartement);
    Page<Departement> findByLastNameContaining(String name, Pageable page);
    void  deleteById(Long theId);
}
