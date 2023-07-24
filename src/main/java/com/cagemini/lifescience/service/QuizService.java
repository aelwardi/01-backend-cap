package com.cagemini.lifescience.service;

import com.cagemini.lifescience.entity.Proposition;
import com.cagemini.lifescience.entity.Quiz;
import com.cagemini.lifescience.model.QuizDTO;

import java.util.List;
import java.util.Optional;

public interface QuizService {

    Quiz save(Long chapitreId, Quiz theQuiz);
    Optional<Quiz> findById(Long theId);
    Quiz updateQuiz(Long chapitreId, Long quizId, Quiz updatedQuiz);
    void  deleteById(Long theId, Long chapitreId);
    public Quiz addQuizWithPropositions(Quiz quiz, List<Proposition> propositions, Long chapitreId);
    List<QuizDTO> getQuizWithPropositionsByChapitreId(Long chapitreId);
}
