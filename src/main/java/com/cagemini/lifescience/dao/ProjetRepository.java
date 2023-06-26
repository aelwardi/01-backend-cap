package com.cagemini.lifescience.dao;

import com.cagemini.lifescience.entity.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200/")
@Repository
public interface ProjetRepository extends JpaRepository<Projet, Long> {
}
