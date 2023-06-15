package com.cagemini.lifescience.service;

import com.cagemini.lifescience.dao.ApprenantRepository;

import com.cagemini.lifescience.dao.DepartementRepository;
import com.cagemini.lifescience.entity.Apprenant;
import com.cagemini.lifescience.entity.Departement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
@Service

public class ApprenantServiceImpl implements ApprenantService {
    private ApprenantRepository apprenantRepository;

    @Autowired
    private DepartementRepository departementRepository;

    @Autowired
    public ApprenantServiceImpl(ApprenantRepository theApprenantRepository){

        apprenantRepository=theApprenantRepository;
    }
    @Autowired
    public List<Apprenant> findAll(){

        return apprenantRepository.findAll();
    }
    @Override
    public Apprenant findById(Long theId) {
        Optional<Apprenant> result=apprenantRepository.findById(theId);

        Apprenant theApprenant = null;
        if (result.isPresent()){
            theApprenant = result.get();
        }
        else {
            throw new RuntimeException("didn't find apprenant id:"+theId);
        }
        return theApprenant;
    }

    @Override
    public Apprenant save(Apprenant theApprenanat) {
        return apprenantRepository.save(theApprenanat);
    }

    @Override
    public Apprenant updateApprenant(Apprenant theApprenanat) {
        return apprenantRepository.save(theApprenanat );
    }

    @Override
    public List<Apprenant> searchByNameOrLastName(@RequestParam("term") String term) {
        return apprenantRepository.searchByNameOrLastName(term);
    }

    @Override
    public void deleteById(Long theId) {
        apprenantRepository.deleteById(theId);

    }

    @Override
    public Apprenant getApprenantByAdminIdAndApprenantId(Long adminId, Long apprenantId) {
        return apprenantRepository.findByAdminIdAndId(adminId, apprenantId);
    }
//    @Override
//    public Apprenant getApprenantByAdminIdAndApprenantId(Long adminId, Long apprenantId) {
//        return apprenantRepository.findByAdminIdAndId(adminId, apprenantId);
//    }



//    @Override
//    public List<Departement> getAllDepartement() {
//        return departementRepository.findAll();
//    }
}
