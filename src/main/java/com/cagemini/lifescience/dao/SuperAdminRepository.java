package com.cagemini.lifescience.dao;

import com.cagemini.lifescience.entity.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200/")
<<<<<<< HEAD
@Repository
=======
>>>>>>> 268d89f119d99662c4afe3f2a2e1c2eba3eb9e0e
public interface SuperAdminRepository extends JpaRepository<SuperAdmin, Long>  {
}
