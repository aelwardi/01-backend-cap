package com.cagemini.lifescience.dao;

import com.cagemini.lifescience.entity.Apprenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Set;

@Repository

@CrossOrigin("http://localhost:4200/")
public interface ApprenantRepository extends JpaRepository<Apprenant,Long> {
    @Query("SELECT a FROM Apprenant a WHERE a.firstName LIKE %:term% OR a.lastName LIKE %:term%")
    List<Apprenant> searchByNameOrLastName(@Param("term") String term);
    Set<Apprenant> findByAdminId(Long adminId);
    Apprenant findByAdminIdAndId(Long adminId, Long apprenantId);
}