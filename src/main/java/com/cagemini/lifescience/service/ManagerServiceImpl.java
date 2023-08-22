package com.cagemini.lifescience.service;

import com.cagemini.lifescience.dao.AdminRepository;
import com.cagemini.lifescience.dao.DepartementRepository;
import com.cagemini.lifescience.dao.ManagerRepository;
import com.cagemini.lifescience.entity.Admin;
import com.cagemini.lifescience.entity.Departement;
import com.cagemini.lifescience.entity.Manager;
import com.cagemini.lifescience.entity.SuperAdmin;
import com.cagemini.lifescience.model.AdminInfo;
import com.cagemini.lifescience.model.DepartementInfo;
import com.cagemini.lifescience.model.ManagerInfos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;
    private final DepartementRepository departementRepository;
    private final AdminRepository adminRepository;

    @Autowired
    public ManagerServiceImpl(ManagerRepository managerRepository, DepartementRepository departementRepository, AdminRepository adminRepository) {
        this.managerRepository = managerRepository;
        this.departementRepository = departementRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public List<ManagerInfos> findAll() {
        List<ManagerInfos> managerInfos = new ArrayList<>();
        List<Manager> managerList = managerRepository.findAll();
        for (Manager manager: managerList){
            Departement departement = manager.getDepartement();
            DepartementInfo departementInfo = new DepartementInfo(departement.getId(), departement.getName());

            managerInfos.add(new ManagerInfos(manager.getId(), manager.getLastName(), manager.getFirstName(), manager.getDateBirth(), manager.getPhone(), manager.getSexe(), manager.getEmail(), manager.getPassword(), manager.getEtat(), manager.getPhoto(), manager.getRole(), departementInfo));
        }
        return managerInfos;
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
    public ManagerInfos getManagerDetails(Long theId) {
        Optional<Manager> result = managerRepository.findById(theId);
        ManagerInfos managerInfos = new ManagerInfos();
        if (result.isPresent()){
            Manager manager = result.get();
            Departement departement = manager.getDepartement();
            DepartementInfo departementInfo = new DepartementInfo(departement.getId(), departement.getName());
            managerInfos = new ManagerInfos(manager.getId(), manager.getLastName(), manager.getFirstName(), manager.getDateBirth(), manager.getPhone(), manager.getSexe(), manager.getEmail(), manager.getPassword(), manager.getEtat(), manager.getPhoto(), manager.getRole(), departementInfo);
        }
        else {
            throw new RuntimeException("didn't find admin id:"+theId);
        }
        return managerInfos;
    }

    @Override
    public Manager save(Long adminId, Manager theManager) {
        Admin admin = this.adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with ID: " + adminId));
        Departement departement = this.departementRepository.findById(theManager.getDepartement().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Departement not found with ID: " + theManager.getDepartement().getId()));
        theManager.setAdmin(admin);
        return managerRepository.save(theManager);
    }

    @Override
    public Manager updateManager(Long adminId, Long id, Manager theManager) {
        Manager manager = this.managerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manager not found with ID: " + id));
        Admin admin = this.adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with ID: " + adminId));
        manager.setFirstName(theManager.getFirstName());
        manager.setLastName(theManager.getLastName());
        manager.setDateBirth(theManager.getDateBirth());
        manager.setPhone(theManager.getPhone());
        manager.setSexe(theManager.getSexe());
        manager.setEmail(theManager.getEmail());
        manager.setEtat(theManager.getEtat());
        manager.setPhoto(theManager.getPhoto());
        return managerRepository.save(manager);
    }

    @Override
    public List<ManagerInfos> searchByNameOrLastName(@RequestParam("adminId") Long adminId ,@RequestParam("term") String term) {
        List<ManagerInfos> managerInfos = new ArrayList<>();
        Admin admin = adminRepository.findById(adminId).orElse(null);
        if (admin != null) {
            List<Manager> managerList =  managerRepository.searchByNameOrLastName(adminId ,term);

            for (Manager manager: managerList) {
                Departement departement = manager.getDepartement();
                DepartementInfo departementInfo = new DepartementInfo(departement.getId(), departement.getName());

                managerInfos.add(new ManagerInfos(manager.getId(), manager.getLastName(), manager.getFirstName(), manager.getDateBirth(), manager.getPhone(), manager.getSexe(), manager.getEmail(), manager.getPassword(), manager.getEtat(), manager.getPhoto(), manager.getRole(), departementInfo));
            }
        }
        else {
            throw new RuntimeException("didn't find admin id:" + adminId);
        }
        return managerInfos;
    }

    @Override
    public ManagerInfos getManagerByAdminIdAndManagerId(Long adminId, Long managerId) {
        Admin admin = this.adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with ID: " + adminId));
        Manager manager = this.managerRepository.findById(managerId)
                .orElseThrow(() -> new ResourceNotFoundException("Manager not found with ID: " + managerId));
        Departement departement = manager.getDepartement();
        DepartementInfo departementInfo = new DepartementInfo(departement.getId(), departement.getName());
        ManagerInfos managerInfos = new ManagerInfos(manager.getId(), manager.getLastName(), manager.getFirstName(), manager.getDateBirth(), manager.getPhone(), manager.getSexe(), manager.getEmail(), manager.getPassword(), manager.getEtat(), manager.getPhoto(), manager.getRole(), departementInfo);
        return managerInfos;
    }

    @Override
    public Manager updateProfile(Long id, Manager theManager) {
        Manager manager = this.managerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manager not found with ID: " + id));
        manager.setDateBirth(theManager.getDateBirth());
        manager.setPhone(theManager.getPhone());
        manager.setPhoto(theManager.getPhoto());
        return managerRepository.save(manager);
    }

}
