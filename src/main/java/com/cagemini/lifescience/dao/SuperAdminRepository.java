package com.cagemini.lifescience.dao;

import com.cagemini.lifescience.entity.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
public interface SuperAdminRepository extends JpaRepository<SuperAdmin, Long>  {
}
