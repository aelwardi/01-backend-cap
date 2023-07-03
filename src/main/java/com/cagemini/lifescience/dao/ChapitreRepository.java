package com.cagemini.lifescience.dao;

import com.cagemini.lifescience.entity.Chapitre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin("http://localhost:4200/")
public interface ChapitreRepository extends JpaRepository<Chapitre,Long> {
}
