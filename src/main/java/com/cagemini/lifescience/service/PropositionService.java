package com.cagemini.lifescience.service;

import com.cagemini.lifescience.entity.Proposition;

public interface PropositionService {

    Proposition save(Long quiz_id, Proposition theProposition);
    Proposition updateProposition(Long quizId, Long propositionId, Proposition theProposition);
    Proposition findById(Long theId);
    void  deleteById(Long theId, Long quizId);
}
