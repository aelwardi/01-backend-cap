package com.cagemini.lifescience.dao;
import com.cagemini.lifescience.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Set;



@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {

    @Query("SELECT m FROM Manager m JOIN m.admin a WHERE a.id = :adminId AND (m.firstName LIKE %:term% OR m.lastName LIKE %:term%)")
    List<Manager> searchByNameOrLastName(@Param("adminId") Long adminId, @Param("term") String term);
    List<Manager> findByAdminId(Long adminId);
    Manager findByAdminIdAndId(Long adminId, Long managerId);
    @Query("SELECT m FROM Manager m WHERE m.departement.id = :departementId AND m NOT IN (SELECT mc.manager FROM ManagerCours mc WHERE mc.cours.id = :coursId)")
    List<Manager> findManagersNotInManagerCoursByCoursIdAndDepartementId(@Param("coursId") Long coursId, @Param("departementId") Long departementId);
}
