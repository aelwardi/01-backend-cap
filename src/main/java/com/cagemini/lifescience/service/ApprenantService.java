package com.cagemini.lifescience.service;


import com.cagemini.lifescience.entity.Apprenant;
import com.cagemini.lifescience.entity.Departement;

import java.util.List;

public interface ApprenantService {
    List<Apprenant> findAll();

    Apprenant findById(Long theId);

    Apprenant save(Apprenant theApprenant);

    Apprenant updateApprenant(Apprenant theApprenant);

    List<Apprenant> searchByNameOrLastName(String term);

    void  deleteById(Long theId);
//    List<Departement> getAllDepartement();

    Apprenant getApprenantByAdminIdAndApprenantId(Long adminId, Long apprenantId);
}
