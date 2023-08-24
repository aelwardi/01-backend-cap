package com.cagemini.lifescience.dao;

import com.cagemini.lifescience.entity.Chapitre;
import com.cagemini.lifescience.entity.SupportCours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200/")
@Repository
public interface SupportCoursRepository extends JpaRepository<SupportCours, Long> {

    SupportCours findByChapitre(Chapitre chapitre);

}