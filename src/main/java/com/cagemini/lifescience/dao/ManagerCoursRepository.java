package com.cagemini.lifescience.dao;

import com.cagemini.lifescience.entity.Cours;
import com.cagemini.lifescience.entity.ManagerCours;
import com.cagemini.lifescience.entity.ManagerCoursId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerCoursRepository extends JpaRepository<ManagerCours, ManagerCoursId> {
    List<ManagerCours> findByCours(Cours cours);
    List<ManagerCours> findAllByCoursId(Long coursId);
}
