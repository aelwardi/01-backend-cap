package com.cagemini.lifescience.service;

import com.cagemini.lifescience.dao.PropositionRepository;
import com.cagemini.lifescience.dao.QuizRepository;
import com.cagemini.lifescience.entity.Proposition;
import com.cagemini.lifescience.entity.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PropositionServiceImpl implements PropositionService {

    private final PropositionRepository propositionRepository;
    private final QuizRepository quizRepository;

    @Autowired
    public PropositionServiceImpl(PropositionRepository propositionRepository, QuizRepository quizRepository) {
        this.propositionRepository = propositionRepository;
        this.quizRepository = quizRepository;
    }

    @Override
    public Proposition findById(Long theId) {
        Optional<Proposition> result = propositionRepository.findById(theId);
        Proposition theProposition = null;
        if(result.isPresent()){
            theProposition = result.get();
        } else {
            throw new RuntimeException("didn't find the Propodition with ID : " + theId);
        }
        return theProposition;
    }

    @Override
    public Proposition save(Long quizId, Proposition theProposition) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new  IllegalArgumentException("Quiz not found with ID : " + quizId));
        theProposition.setQuiz(quiz);
        return propositionRepository.save(theProposition);
    }

    @Override
    public Proposition updateProposition(Long quizId, Long propositionId, Proposition theProposition) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found with ID : " + quizId));
        Proposition proposition = propositionRepository.findById(propositionId)
                .orElseThrow(() -> new IllegalArgumentException("Proposition not found with ID : " + propositionId));

        if(!proposition.getQuiz().getId().equals(quizId)){
            throw new IllegalArgumentException("Proposition with ID: " + propositionId + " is not associated with Quiz with ID: " + quizId);
        }

        proposition.setResponse(theProposition.getResponse());
        proposition.setCorrecte(theProposition.getCorrecte());
        return propositionRepository.save(proposition);
    }

    @Override
    public void deleteById(Long theId, Long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found with ID : " + quizId));
        Proposition proposition = propositionRepository.findById(theId)
                .orElseThrow(() -> new IllegalArgumentException("Proposition not found with ID : " + theId));
        if(!proposition.getQuiz().getId().equals(quizId)){
            throw new IllegalArgumentException("Proposition with ID: " + theId + " is not associated with Quiz with ID: " + quizId);
        }
        propositionRepository.deleteById(theId);
    }
}
