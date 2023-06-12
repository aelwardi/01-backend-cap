package com.cagemini.lifescience.dao;

import com.cagemini.lifescience.entity.Admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;
=======
>>>>>>> 268d89f119d99662c4afe3f2a2e1c2eba3eb9e0e
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200/")
<<<<<<< HEAD
@Repository
=======
>>>>>>> 268d89f119d99662c4afe3f2a2e1c2eba3eb9e0e
public interface AdminRepository extends JpaRepository<Admin, Long> {
//    Page<Admin>findByFirstNameContaining(@Param("firstName") String firstName, Pageable page);
    @Query("SELECT a FROM Admin a WHERE a.firstName LIKE %:term% OR a.lastName LIKE %:term%")

    List<Admin> searchByNameOrLastName(@Param("term") String term);

}
