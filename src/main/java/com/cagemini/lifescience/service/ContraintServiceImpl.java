package com.cagemini.lifescience.service;

import com.cagemini.lifescience.dao.ContraintRepository;
import com.cagemini.lifescience.dao.CoursRepository;
import com.cagemini.lifescience.entity.Contraint;
import com.cagemini.lifescience.entity.Cours;
import com.cagemini.lifescience.model.ContraintInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContraintServiceImpl implements ContraintSevice {

    private final ContraintRepository contraintRepository;
    private final CoursRepository coursRepository;

    @Autowired
    public ContraintServiceImpl(ContraintRepository contraintRepository, CoursRepository coursRepository) {
        this.contraintRepository = contraintRepository;
        this.coursRepository = coursRepository;
    }

    @Override
    public Contraint save(Long coursId, Contraint theContraint) {
        Cours cours = coursRepository.findById(coursId)
                .orElseThrow(() -> new IllegalArgumentException("Cours not found with ID: " + coursId));
        theContraint.setCours(cours);
        return contraintRepository.save(theContraint);
    }

    @Override
    public ContraintInfo getContraintByCoursID(Long coursId) {
        Cours cours = coursRepository.findById(coursId)
                .orElseThrow(() -> new IllegalArgumentException("Cours not found with ID: " + coursId));
        Contraint contraint = cours.getContraint();
        ContraintInfo contraintInfo = new ContraintInfo();
        if (contraint != null){
            contraintInfo.setId(contraint.getId());
            contraintInfo.setStartCourse(contraint.getStartCourse());
            contraintInfo.setEndCourse(contraint.getEndCourse());
        }
        return contraintInfo;
    }

    @Override
    public Contraint updateContraint(Long contraintId, Long coursId, Contraint theContraint) {
        Cours cours = coursRepository.findById(coursId)
                .orElseThrow(() -> new IllegalArgumentException("Cours not found with ID: " + coursId));
        Contraint contraint = contraintRepository.findById(contraintId)
                .orElseThrow(() -> new IllegalArgumentException("Contraint not found with ID: " + contraintId));
        if(!cours.getContraint().getId().equals(contraintId)){
            throw new IllegalArgumentException("Contraint with ID " + contraintId + " is not associated with Cours with ID " + coursId);
        }
        contraint.setStartCourse(theContraint.getStartCourse());
        contraint.setEndCourse(theContraint.getEndCourse());
        return contraintRepository.save(contraint);
    }

    @Override
    public void deleteContraint(Long contraintId, Long coursId) {
        Cours cours = coursRepository.findById(coursId)
                .orElseThrow(() -> new IllegalArgumentException("Cours not found with ID: " + coursId));
        Contraint contraint = contraintRepository.findById(contraintId)
                .orElseThrow(() -> new IllegalArgumentException("Contraint not found with ID: " + contraintId));
        if(!cours.getContraint().getId().equals(contraintId)){
            throw new IllegalArgumentException("Contraint with ID " + contraintId + " is not associated with Cours with ID " + coursId);
        }
        //System.out.println("ok");
        contraintRepository.deleteContraint(contraintId, coursId);
    }
}
