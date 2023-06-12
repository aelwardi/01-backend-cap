package com.cagemini.lifescience.service;

import com.cagemini.lifescience.entity.Admin;
import com.cagemini.lifescience.entity.Manager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ManagerService {

    List<Manager> findAll();
    Manager findById(Long theId);
    Manager save(Manager theManager);
    Manager updateManager(Manager theManager);
}
