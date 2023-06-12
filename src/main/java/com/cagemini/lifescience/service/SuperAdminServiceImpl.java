package com.cagemini.lifescience.service;

import com.cagemini.lifescience.dao.SuperAdminRepository;
<<<<<<< HEAD
import com.cagemini.lifescience.entity.Role;
=======
>>>>>>> 268d89f119d99662c4afe3f2a2e1c2eba3eb9e0e
import com.cagemini.lifescience.entity.SuperAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
<<<<<<< HEAD
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
=======

>>>>>>> 268d89f119d99662c4afe3f2a2e1c2eba3eb9e0e
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
<<<<<<< HEAD
    public String save(MultipartFile photo, String firstName, String lastName, Date dateOfBirth, String sexe, String email, String password, Boolean etat, String phone, String role) throws IOException {
        SuperAdmin superAdmin = new SuperAdmin();
        superAdmin.setFirstName(firstName);
        superAdmin.setLastName(lastName);
        superAdmin.setDateOfBirth(dateOfBirth);
        superAdmin.setEmail(email);
        superAdmin.setSexe(sexe);
        superAdmin.setPassword(password);
        superAdmin.setEtat(etat);
        superAdmin.setPhone(phone);
        superAdmin.setRole(Role.valueOf(role));
        superAdmin.setPhoto(photo.getBytes());
        superAdminRepository.save(superAdmin);
        return "Image saved DB";
    }

    @Override
    public SuperAdmin updateSuperAdmin(SuperAdmin theSuperAdmin) throws IOException {
=======
    public SuperAdmin save(SuperAdmin theSuperAdmin) {
        return superAdminRepository.save(theSuperAdmin);
    }

    @Override
    public SuperAdmin updateSuperAdmin(SuperAdmin theSuperAdmin) {
>>>>>>> 268d89f119d99662c4afe3f2a2e1c2eba3eb9e0e
        return superAdminRepository.save(theSuperAdmin);
    }

}
