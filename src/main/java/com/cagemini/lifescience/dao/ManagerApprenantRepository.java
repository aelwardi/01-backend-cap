package com.cagemini.lifescience.dao;

import com.cagemini.lifescience.entity.Manager;
import com.cagemini.lifescience.entity.ManagerApprenant;
import com.cagemini.lifescience.entity.ManagerApprenantId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
public interface ManagerApprenantRepository extends JpaRepository<ManagerApprenant, ManagerApprenantId> {
    List<ManagerApprenant> findByManager(Manager manager);
}
