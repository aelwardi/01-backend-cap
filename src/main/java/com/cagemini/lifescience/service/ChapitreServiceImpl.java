package com.cagemini.lifescience.service;

import com.cagemini.lifescience.dao.ChapitreRepository;
import com.cagemini.lifescience.dao.CoursRepository;
import com.cagemini.lifescience.entity.Chapitre;
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

    @Autowired
    public ChapitreServiceImpl(ChapitreRepository thechapitreRepository) {
        chapitreRepository = thechapitreRepository;
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
    public Chapitre save(Chapitre theChapitre) {
        return chapitreRepository.save(theChapitre);
    }

    @Override
    public Chapitre updateChapitre(Chapitre theChapitre) {
        return chapitreRepository.save(theChapitre);
    }

    @Override
    public void deleteById(Long theId) {
        chapitreRepository.deleteById(theId);
    }


}

