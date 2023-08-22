package com.cagemini.lifescience.dao;

import com.cagemini.lifescience.entity.Contraint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContraintRepository extends JpaRepository<Contraint, Long> {
    @Modifying
    @Query("DELETE FROM Contraint c WHERE c.id = :contraintId AND c.cours.id = :coursId")
    void deleteContraint(@Param("contraintId") Long contraintId, @Param("coursId") Long chapitreId);
}
