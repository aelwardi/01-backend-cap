package com.capgemini.lifescience.dao;
import com.capgemini.lifescience.entity.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200/")
public interface SuperAdminRepository extends JpaRepository<SuperAdmin, Long> {
}
