package com.cagemini.lifescience.service;

import com.cagemini.lifescience.entity.Proposition;
import com.cagemini.lifescience.entity.Quiz;

import java.util.List;
import java.util.Optional;

public interface QuizService {

    Quiz save(Long chapitreId, Quiz theQuiz);
    Optional<Quiz> findById(Long theId);
    public Quiz updateQuiz(Long chapitreId, Long quizId, Quiz updatedQuiz);
    void  deleteById(Long theId, Long chapitreId);
    List<Proposition> getPropositionByQuiz(Long quizId);
}
