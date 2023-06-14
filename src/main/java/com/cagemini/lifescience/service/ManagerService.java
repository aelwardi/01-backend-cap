package com.cagemini.lifescience.service;

import com.cagemini.lifescience.entity.Manager;

import java.util.List;

public interface ManagerService {

    List<Manager> findAll();
    Manager findById(Long theId);
    Manager save(Manager theManager);
    Manager updateManager(Manager theManager);
    List<Manager> searchByNameOrLastName(String term);
}
