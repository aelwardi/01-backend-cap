package com.cagemini.lifescience.dao;

import com.cagemini.lifescience.entity.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200/")
@Repository
public interface ProjetRepository extends JpaRepository<Projet, Long> {

    @Query("SELECT p FROM Projet p WHERE (p.name LIKE %:term% OR p.nameClient LIKE %:term%) AND p.departement.id = :departementId")
    List<Projet> searchByNameOrNameClientAndDepartementId(@Param("term") String term, @Param("departementId") Long departementId);
}
