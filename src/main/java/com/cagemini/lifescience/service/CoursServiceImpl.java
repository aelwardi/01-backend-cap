package com.cagemini.lifescience.service;


import com.cagemini.lifescience.dao.CoursRepository;
import com.cagemini.lifescience.entity.Cours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
@Service
public class CoursServiceImpl implements CoursService{

    private CoursRepository coursRepository;


    @Autowired
    public CoursServiceImpl(CoursRepository theCoursRepository){

        coursRepository=theCoursRepository;
    }
    @Autowired
    public List<Cours> findAll(){

        return coursRepository.findAll();
    }
    @Override
    public Cours findById(Long theId) {
        Optional<Cours> result=coursRepository.findById(theId);

        Cours theCours = null;
        if (result.isPresent()){
            theCours = result.get();
        }
        else {
            throw new RuntimeException("didn't find cours id:"+theId);
        }
        return theCours;
    }

    @Override
    public Cours save(Cours theCours) {
        return coursRepository.save(theCours);
    }

    @Override
    public Cours updateCours(Cours theCours) {
        return coursRepository.save(theCours );
    }

    @Override
    public List<Cours> searchByTitle(@RequestParam("term") String term) {
        return coursRepository.searchByTitle(term);
    }

    @Override
    public void deleteById(Long theId) {
        coursRepository.deleteById(theId);

    }
}
