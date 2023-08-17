package com.cagemini.lifescience.dao;

import com.cagemini.lifescience.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByChapitreId(Long chapitreId);
    @Modifying
    @Query("DELETE FROM Quiz q WHERE q.id = :quizId AND q.chapitre.id = :chapitreId")
    void deleteQuizWithPropositions(@Param("quizId") Long quizId, @Param("chapitreId") Long chapitreId);
}
