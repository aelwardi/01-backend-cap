package com.cagemini.lifescience.service;

import com.cagemini.lifescience.dao.ManagerRepository;
import com.cagemini.lifescience.entity.Admin;
import com.cagemini.lifescience.entity.Manager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {

    private ManagerRepository managerRepository;

    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public List<Manager> findAll() {
        return managerRepository.findAll();
    }

    @Override
    public Manager findById(Long theId) {
        Optional<Manager> result=managerRepository.findById(theId);

        Manager theManager =null;
        if (result.isPresent()){
            theManager = result.get();
        }
        else {
            throw new RuntimeException("didn't find manager id:"+theId);
        }
        return theManager;
    }

    @Override
    public Manager save(Manager theManager) {
        return managerRepository.save(theManager);
    }

    @Override
    public Manager updateManager(Manager theManager) {
        return managerRepository.save(theManager );
    }

}
