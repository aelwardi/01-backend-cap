package com.cagemini.lifescience.service;

import com.cagemini.lifescience.entity.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface AdminService {
    List<Admin>findAll();
    Admin findById(Long theId);
     Admin save(Admin theAdmin);
     Admin updateAdmin(Admin theAdmin);
    List<Admin> searchByNameOrLastName(String term);
     void  deleteById(Long theId);

}
