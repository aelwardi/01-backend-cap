package com.cagemini.lifescience.service;

import com.cagemini.lifescience.entity.Proposition;

public interface PropositionService {

    Proposition findById(Long theId);
    Proposition save(Long quiz_id, Proposition theProposition);
    Proposition updateProposition(Long quizId, Long propositionId, Proposition theProposition);
    void  deleteById(Long theId, Long quizId);
}
