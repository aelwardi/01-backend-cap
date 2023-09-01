package com.cagemini.lifescience.service;


import com.cagemini.lifescience.entity.Apprenant;
import com.cagemini.lifescience.entity.Departement;
import com.cagemini.lifescience.entity.Manager;
import com.cagemini.lifescience.model.ApprenantInfos;
import com.cagemini.lifescience.model.ManagerInfos;

import java.util.List;

public interface ApprenantService {
    List<Apprenant> findAll();
    Apprenant findById(Long theId);
    Apprenant save(Long adminId, Apprenant theApprenant);
    Apprenant updateApprenant(Long adminId, Long id, Apprenant theApprenant);
    List<ApprenantInfos> searchByNameOrLastName(Long adminId, String term);
    void  deleteById(Long theId);
    ApprenantInfos getApprenantByAdminIdAndApprenantId(Long adminId, Long apprenantId);
    ApprenantInfos getApprenantDetails(Long theId);
    Apprenant updateProfile(Long id, Apprenant theApprenant);

}
