package com.cagemini.lifescience.service;

import com.cagemini.lifescience.dao.SuperAdminRepository;
import com.cagemini.lifescience.entity.Role;
import com.cagemini.lifescience.entity.SuperAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
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
        return superAdminRepository.save(theSuperAdmin);
    }

}
