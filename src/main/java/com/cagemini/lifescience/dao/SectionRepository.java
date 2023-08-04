package com.cagemini.lifescience.dao;

import com.cagemini.lifescience.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    List<Section> findByChapitreId(Long chapitreId);

    @Modifying
    @Query("DELETE FROM Section s WHERE s.id = :sectionId AND s.chapitre.id = :chapitreId")
    void deleteBySectionIdAndChapitreId(@Param("sectionId") Long sectionId, @Param("chapitreId") Long chapitreId);
}
