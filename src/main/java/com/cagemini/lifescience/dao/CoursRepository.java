package com.cagemini.lifescience.dao;

import com.cagemini.lifescience.entity.Apprenant;
import com.cagemini.lifescience.entity.Cours;
import com.cagemini.lifescience.entity.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200/")
@Repository
public interface CoursRepository extends JpaRepository<Cours,Long> {


    List<Cours> searchByTitle(@Param("term") String term);
    List<Cours> findByProjet(Projet projet);
}
