package com.cagemini.lifescience.dao;

import com.cagemini.lifescience.entity.Admin;
import com.cagemini.lifescience.entity.Departement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200/")
@Repository
public interface DepartementRepository extends JpaRepository<Departement, Long> {

    Page<Departement> findByNameContaining(@Param("name") String name, Pageable page);
}
