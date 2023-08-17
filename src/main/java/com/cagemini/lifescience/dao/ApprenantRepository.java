package com.cagemini.lifescience.dao;

import com.cagemini.lifescience.entity.Apprenant;
import com.cagemini.lifescience.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Set;

@Repository
public interface ApprenantRepository extends JpaRepository<Apprenant,Long> {
    @Query("SELECT a FROM Apprenant a JOIN a.admin ad WHERE ad.id = :adminId AND (a.firstName LIKE %:term% OR a.lastName LIKE %:term%)")
    List<Apprenant> searchByNameOrLastName(@Param("adminId") Long adminId, @Param("term") String term);
    List<Apprenant> findByAdminId(Long adminId);
    Apprenant findByAdminIdAndId(Long adminId, Long apprenantId);
}