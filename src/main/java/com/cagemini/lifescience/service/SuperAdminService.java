package com.cagemini.lifescience.service;


import com.cagemini.lifescience.entity.Departement;
import com.cagemini.lifescience.entity.SuperAdmin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface SuperAdminService {

    SuperAdmin findById(Long theId);
    String save(MultipartFile photo, String firstName, String lastName, Date dateOfBirth, String sexe, String email, String password, Boolean etat, String phone, String role) throws IOException;
    SuperAdmin updateSuperAdmin(SuperAdmin theSuperAdmin) throws IOException;

}
