package com.cagemini.lifescience.rest;

import com.cagemini.lifescience.entity.Proposition;
import com.cagemini.lifescience.model.ApiResponse;
import com.cagemini.lifescience.service.PropositionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PropositionController {
    private PropositionServiceImpl propositionService;

    @Autowired
    public PropositionController(PropositionServiceImpl propositionService) {
        this.propositionService = propositionService;
    }

    @PostMapping("/propositions")
    public Proposition addProposition(@RequestParam Long quizId, @RequestBody Proposition theProposition) {
        theProposition.setId(0L);
        Proposition dbProposition = propositionService.save(quizId,theProposition);
        return dbProposition;
    }

    @PutMapping("/propositions/{quizId}/{propositionId}")
    public ResponseEntity<Proposition> updateProposition(@PathVariable Long quizId, @PathVariable Long propositionId, @RequestBody Proposition thepropositions){
        Proposition dbpropositions = propositionService.updateProposition(quizId, propositionId, thepropositions);
        return ResponseEntity.ok(dbpropositions);
    }

    @DeleteMapping("/propositions/{theId}/{quizId}")
    public ApiResponse deleteProposition(@PathVariable Long theId, @PathVariable Long quizId){
        propositionService.deleteById(theId, quizId);
        return (new ApiResponse("Deleted proposition ID : " + theId));
    }
}
