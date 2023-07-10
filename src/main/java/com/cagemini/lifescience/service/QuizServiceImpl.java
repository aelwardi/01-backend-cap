package com.cagemini.lifescience.service;

import com.cagemini.lifescience.dao.ChapitreRepository;
import com.cagemini.lifescience.dao.QuizRepository;
import com.cagemini.lifescience.entity.Chapitre;
import com.cagemini.lifescience.entity.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final ChapitreRepository chapitreRepository;

    @Autowired
    public QuizServiceImpl(QuizRepository quizRepository, ChapitreRepository chapitreRepository) {
        this.quizRepository = quizRepository;
        this.chapitreRepository = chapitreRepository;
    }

    @Override
    public Quiz save(Long chapitreId, Quiz theQuiz) {
        Chapitre chapitre = chapitreRepository.findById(chapitreId)
                .orElseThrow(() -> new IllegalArgumentException("Chapitre not found with ID: " + chapitreId));

        theQuiz.setChapitre(chapitre);
        return quizRepository.save(theQuiz);
    }

    @Override
    public Optional<Quiz> findById(Long theId) {
        return Optional.empty();
    }


    @Override
    public Quiz updateQuiz(Long chapitreId, Long quizId, Quiz updatedQuiz) {
        Chapitre chapitre = chapitreRepository.findById(chapitreId)
                .orElseThrow(() -> new IllegalArgumentException("Chapitre not found with ID: " + chapitreId));

        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found with ID: " + quizId));

        if(!quiz.getChapitre().getId().equals(chapitreId)){
            throw new IllegalArgumentException("Quiz with ID " + quizId + " is not associated with Chapitre with ID " + chapitreId);
        }

        quiz.setQuestion(updatedQuiz.getQuestion());
        quiz.setDateUpdated(new Date());

        return quizRepository.save(quiz);
    }

    @Override
    public void deleteById(Long theId, Long chapitreId) {
        Chapitre chapitre = chapitreRepository.findById(chapitreId)
                .orElseThrow(() -> new IllegalArgumentException("Chapitre not found with ID: " + chapitreId));

        Quiz quiz = quizRepository.findById(theId)
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found with ID: " + theId));

        if(!quiz.getChapitre().getId().equals(chapitreId)){
            throw new IllegalArgumentException("Quiz with ID " + theId + " is not associated with Chapitre with ID " + chapitreId);
        }

        quizRepository.deleteById(theId);
    }
}
