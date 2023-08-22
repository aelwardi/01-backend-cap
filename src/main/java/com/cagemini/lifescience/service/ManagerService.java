package com.cagemini.lifescience.service;

import com.cagemini.lifescience.entity.Admin;
import com.cagemini.lifescience.entity.Manager;
import com.cagemini.lifescience.model.ManagerInfos;

import java.util.List;

public interface ManagerService {

    List<ManagerInfos> findAll();
    Manager findById(Long theId);
    ManagerInfos getManagerDetails(Long theId);
    Manager save(Long adminId, Manager theManager);
    Manager updateManager(Long adminId, Long id ,Manager theManager);
    List<ManagerInfos> searchByNameOrLastName(Long adminId, String term);
    ManagerInfos getManagerByAdminIdAndManagerId(Long adminId, Long managerId);
    Manager updateProfile(Long id, Manager theManager);
}
