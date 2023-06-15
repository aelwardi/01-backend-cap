package com.cagemini.lifescience.dao;

import com.cagemini.lifescience.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Set;

@CrossOrigin("http://localhost:4200/")
@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {

    @Query("SELECT m FROM Manager m JOIN m.admin a WHERE a.id = :adminId AND (m.firstName LIKE %:term% OR m.lastName LIKE %:term%)")
    List<Manager> searchByNameOrLastName(@Param("adminId") Long adminId, @Param("term") String term);
    Set<Manager> findByAdminId(Long adminId);
    Manager findByAdminIdAndId(Long adminId, Long managerId);
}
