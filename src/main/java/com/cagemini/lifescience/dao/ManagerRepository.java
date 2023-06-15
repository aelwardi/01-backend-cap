package com.cagemini.lifescience.dao;
import com.cagemini.lifescience.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;


@Repository
@CrossOrigin("http://localhost:4200/")
public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
