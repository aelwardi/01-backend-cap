package com.cagemini.lifescience.service;

import com.cagemini.lifescience.dao.ChapitreRepository;
import com.cagemini.lifescience.dao.PropositionRepository;
import com.cagemini.lifescience.dao.QuizRepository;
import com.cagemini.lifescience.entity.Chapitre;
import com.cagemini.lifescience.entity.Proposition;
import com.cagemini.lifescience.entity.Quiz;
import com.cagemini.lifescience.model.PropositionInfo;
import com.cagemini.lifescience.model.QuizDTO;
import com.cagemini.lifescience.model.QuizInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final PropositionRepository propositionRepository;
    private final ChapitreRepository chapitreRepository;

    @Autowired
    public QuizServiceImpl(QuizRepository quizRepository, ChapitreRepository chapitreRepository, PropositionRepository propositionRepository) {
        this.quizRepository = quizRepository;
        this.chapitreRepository = chapitreRepository;
        this.propositionRepository = propositionRepository;
    }
    @Override
    public List<QuizDTO> getQuizWithPropositionsByChapitreId(Long chapitreId) {
        List<Quiz> quizzes = quizRepository.findByChapitreId(chapitreId);
        List<QuizDTO> quizDTOs = new ArrayList<>();

        for (Quiz quiz : quizzes) {
            QuizInfo quizInfo = new QuizInfo(quiz.getId(), quiz.getQuestion(), quiz.getDateCreated(), quiz.getDateUpdated());
            List<Proposition> propositionList = propositionRepository.findByQuizId(quiz.getId());
            List<PropositionInfo> propositionInfos = new ArrayList<>();
            for (Proposition proposition: propositionList){
                propositionInfos.add(new PropositionInfo(proposition.getId(), proposition.getResponse(), proposition.getCorrecte()));
            }
            quizDTOs.add(new QuizDTO(quizInfo, propositionInfos));
        }

        return quizDTOs;
    }
    @Override
    public Quiz addQuizWithPropositions(QuizInfo quiz, List<PropositionInfo> propositions, Long chapitreId) {
        Quiz quizer = new Quiz();
        quizer.setQuestion(quiz.getQuestion());
        quizer.setDateCreated(new Date());
        quizer.setDateUpdated(new Date());

        List<Proposition> prospals = new ArrayList<>();
        for (PropositionInfo propositionInfo: propositions){
            Proposition proposition = new Proposition();
            proposition.setResponse(propositionInfo.getResponse());
            proposition.setCorrecte(propositionInfo.getCorrecte());
            proposition.setQuiz(quizer); // Set the quiz for each proposition
            prospals.add(proposition);
        }

        quizer.setPropositions(prospals);

        Chapitre chapitre = chapitreRepository.findById(chapitreId)
                .orElseThrow(() -> new IllegalArgumentException("Chapitre not found with ID: " + chapitreId));
        quizer.setChapitre(chapitre);

        quizer = quizRepository.save(quizer);

        return quizer;
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
    public void deleteById(Long theId, Long chapitreId) {
        Chapitre chapitre = chapitreRepository.findById(chapitreId)
                .orElseThrow(() -> new IllegalArgumentException("Chapitre not found with ID: " + chapitreId));

        Quiz quiz = quizRepository.findById(theId)
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found with ID: " + theId));

        if(!quiz.getChapitre().getId().equals(chapitreId)){
            throw new IllegalArgumentException("Quiz with ID " + theId + " is not associated with Chapitre with ID " + chapitreId);
        }
        propositionRepository.deletePropositionsByQuizId(theId);
        quizRepository.deleteQuizWithPropositions(theId, chapitreId);
    }

}
