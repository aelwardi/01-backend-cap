package com.cagemini.lifescience.service;

import com.cagemini.lifescience.dao.ProjetRepository;
import com.cagemini.lifescience.entity.Projet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjetServiceImpl implements ProjetService {

    private ProjetRepository projetRepository;

    @Autowired
    public ProjetServiceImpl(ProjetRepository projetRepository) {
        this.projetRepository = projetRepository;
    }


    @Override
    public Projet save(Projet theProjet) {
        return projetRepository.save(theProjet);
    }

    @Override
    public Projet updateProjet(Projet theProjet) {
        return projetRepository.save(theProjet );
    }

    @Override
    public void deleteById(Long theId) {
        projetRepository.deleteById(theId);
    }

    @Override
    public Projet findById(Long theId) {
        Optional<Projet> result = projetRepository.findById(theId);
        Projet theProjet = null;
        if (result.isPresent()){
            theProjet = result.get();
        }
        else {
            throw new RuntimeException("didn't find project id:"+theId);
        }
        return theProjet;
    }

    @Override
    public List<Projet> searchByNameOrNameClientAndDepartementId(String term, Long departementId) {
        return projetRepository.searchByNameOrNameClientAndDepartementId(term, departementId);
    }

}
