package com.cagemini.lifescience.service;

import com.cagemini.lifescience.dao.ChapitreRepository;
import com.cagemini.lifescience.dao.CoursRepository;
import com.cagemini.lifescience.dao.PropositionRepository;
import com.cagemini.lifescience.dao.QuizRepository;
import com.cagemini.lifescience.entity.Chapitre;
import com.cagemini.lifescience.entity.Cours;
import com.cagemini.lifescience.entity.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ChapitreServiceImpl implements ChapitreService {
    private ChapitreRepository chapitreRepository;
    private final CoursRepository coursRepository;
    private final QuizRepository quizRepository;
    private final PropositionRepository propositionRepository;

    @Autowired
    public ChapitreServiceImpl(ChapitreRepository chapitreRepository, CoursRepository coursRepository,
                               QuizRepository quizRepository, PropositionRepository propositionRepository) {
        this.chapitreRepository = chapitreRepository;
        this.coursRepository = coursRepository;
        this.quizRepository = quizRepository;
        this.propositionRepository = propositionRepository;
    }


    @Override
    public List<Chapitre> findAll() {

        return chapitreRepository.findAll();
    }

    @Override
    public Chapitre findById(Long theId) {
        Optional<Chapitre> result = chapitreRepository.findById(theId);

        Chapitre theChapitre = null;
        if (result.isPresent()) {
            theChapitre = result.get();
        } else {
            throw new RuntimeException("didn't find chapitre id:" + theId);
        }
        return theChapitre;
    }

    @Override
    public Chapitre save(Long coursId,Chapitre theChapitre) {

        Cours cours = coursRepository.findById(coursId)
                .orElseThrow(() -> new IllegalArgumentException("cours not found : " +coursId ));
        theChapitre.setCours(cours);
        return chapitreRepository.save(theChapitre);
    }

    @Override
    public Chapitre updateChapitre(Chapitre theChapitre) {

        return chapitreRepository.save(theChapitre);
    }

    @Override
    public void deleteById(Long theId, Long coursId) {

        Cours cours = coursRepository.findById(coursId)
                .orElseThrow(() -> new IllegalArgumentException("cours not found with ID : " + coursId));
        Chapitre chapitre = chapitreRepository.findById(theId)
                .orElseThrow(() -> new IllegalArgumentException("chapitre not found with ID : " + theId));
        if(!chapitre.getCours().getId().equals(coursId)){
            throw new IllegalArgumentException("chapitre with ID: " + theId + " is not associated with Quiz with ID: " + coursId);
        }

        chapitreRepository.deleteById(theId);
    }


}

