package com.cagemini.lifescience.dao;

import com.cagemini.lifescience.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200/")
@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByChapitreId(Long chapitreId);
}
