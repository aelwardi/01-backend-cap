package com.cagemini.lifescience.service;

import com.cagemini.lifescience.entity.Proposition;
import com.cagemini.lifescience.entity.Quiz;
import com.cagemini.lifescience.model.PropositionInfo;
import com.cagemini.lifescience.model.QuizDTO;
import com.cagemini.lifescience.model.QuizInfo;

import java.util.List;
import java.util.Optional;

public interface QuizService {

    List<QuizDTO> getQuizWithPropositionsByChapitreId(Long chapitreId);
    public Quiz addQuizWithPropositions(QuizInfo quiz, List<PropositionInfo> propositions, Long chapitreId);
    Quiz updateQuiz(Long chapitreId, Long quizId, Quiz updatedQuiz);
    Quiz save(Long chapitreId, Quiz theQuiz);
    Optional<Quiz> findById(Long theId);
    void  deleteById(Long theId, Long chapitreId);
}
