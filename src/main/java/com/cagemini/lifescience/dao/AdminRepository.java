package com.cagemini.lifescience.dao;

import com.cagemini.lifescience.entity.Admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200/")
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
//    Page<Admin>findByFirstNameContaining(@Param("firstName") String firstName, Pageable page);
    @Query("SELECT a FROM Admin a WHERE a.firstName LIKE %:term% OR a.lastName LIKE %:term%")

    List<Admin> searchByNameOrLastName(@Param("term") String term);

}
