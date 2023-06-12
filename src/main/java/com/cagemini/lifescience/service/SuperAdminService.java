package com.cagemini.lifescience.service;


import com.cagemini.lifescience.entity.Departement;
import com.cagemini.lifescience.entity.SuperAdmin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
<<<<<<< HEAD
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
=======

>>>>>>> 268d89f119d99662c4afe3f2a2e1c2eba3eb9e0e
import java.util.List;

public interface SuperAdminService {

    SuperAdmin findById(Long theId);
<<<<<<< HEAD
    String save(MultipartFile photo, String firstName, String lastName, Date dateOfBirth, String sexe, String email, String password, Boolean etat, String phone, String role) throws IOException;
    SuperAdmin updateSuperAdmin(SuperAdmin theSuperAdmin) throws IOException;

=======
    SuperAdmin save(SuperAdmin theSuperAdmin);
    SuperAdmin updateSuperAdmin(SuperAdmin theSuperAdmin);
>>>>>>> 268d89f119d99662c4afe3f2a2e1c2eba3eb9e0e
}
