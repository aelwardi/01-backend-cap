package com.cagemini.lifescience.service;

import com.cagemini.lifescience.entity.Quiz;

import java.util.Optional;

public interface QuizService {

    Quiz save(Long chapitreId, Quiz theQuiz);
    Optional<Quiz> findById(Long theId);
    public Quiz updateQuiz(Long chapitreId, Long quizId, Quiz updatedQuiz);
    void  deleteById(Long theId, Long chapitreId);
}
