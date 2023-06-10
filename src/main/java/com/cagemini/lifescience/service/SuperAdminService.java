package com.cagemini.lifescience.service;


import com.cagemini.lifescience.entity.Departement;
import com.cagemini.lifescience.entity.SuperAdmin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SuperAdminService {

    SuperAdmin findById(Long theId);
    SuperAdmin save(SuperAdmin theSuperAdmin);
    SuperAdmin updateSuperAdmin(SuperAdmin theSuperAdmin);
}
