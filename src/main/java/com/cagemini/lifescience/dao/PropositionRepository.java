package com.cagemini.lifescience.dao;

import com.cagemini.lifescience.entity.Proposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
public interface PropositionRepository extends JpaRepository<Proposition, Long> {
    List<Proposition> findByQuizId(Long quizId);
    @Modifying
    @Query("DELETE FROM Proposition p WHERE p.quiz.id = :quizId")
    void deletePropositionsByQuizId(@Param("quizId") Long quizId);
}
