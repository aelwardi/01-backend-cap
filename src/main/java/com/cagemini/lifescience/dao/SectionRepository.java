package com.cagemini.lifescience.dao;

import com.cagemini.lifescience.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200/")
@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    List<Section> findByChapitreId(Long chapitreId);
}
