package com.cagemini.lifescience.dao;

import com.cagemini.lifescience.entity.ApprenantProjet;
import com.cagemini.lifescience.entity.ApprenantProjetId;
import com.cagemini.lifescience.entity.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprenantProjetRepository extends JpaRepository<ApprenantProjet, ApprenantProjetId> {
    List<ApprenantProjet> findByProjet(Projet projet);
    List<ApprenantProjet> findAllByProjetId(Long projetId);
    List<ApprenantProjet> findByProjetId(Long projetId);
    List<ApprenantProjet> findByApprenantId(Long apprenantId);

}
