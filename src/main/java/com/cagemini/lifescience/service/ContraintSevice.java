package com.cagemini.lifescience.service;


import com.cagemini.lifescience.entity.Contraint;
import com.cagemini.lifescience.model.ContraintInfo;

public interface ContraintSevice {
    Contraint save(Long coursId, Contraint theContraint);
    ContraintInfo getContraintByCoursID(Long coursId);
    Contraint updateContraint(Long contraintId, Long coursId,  Contraint theContraint);
    void  deleteContraint(Long contraintId, Long coursId);
}
