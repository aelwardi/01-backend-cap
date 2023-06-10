package com.cagemini.lifescience.service;

import com.cagemini.lifescience.dao.SuperAdminRepository;
import com.cagemini.lifescience.entity.SuperAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class SuperAdminServiceImpl implements SuperAdminService {

    private SuperAdminRepository superAdminRepository;

    @Autowired
    public SuperAdminServiceImpl(SuperAdminRepository superAdminRepository) {
        this.superAdminRepository = superAdminRepository;
    }

    @Override
    public SuperAdmin findById(Long theId) {
        Optional<SuperAdmin> result=superAdminRepository.findById(theId);
        SuperAdmin theSuperAdmin =null;
        if (result.isPresent()){
            theSuperAdmin = result.get();
        }
        else {
            throw new RuntimeException("didn't find super admin id:"+theId);
        }
        return theSuperAdmin;
    }

    @Override
    public SuperAdmin save(SuperAdmin theSuperAdmin) {
        return superAdminRepository.save(theSuperAdmin);
    }

    @Override
    public SuperAdmin updateSuperAdmin(SuperAdmin theSuperAdmin) {
        return superAdminRepository.save(theSuperAdmin);
    }

}
