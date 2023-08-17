package com.cagemini.lifescience.rest;

import com.cagemini.lifescience.entity.Proposition;
import com.cagemini.lifescience.entity.Quiz;
import com.cagemini.lifescience.model.ApiResponse;
import com.cagemini.lifescience.model.QuizDTO;
import com.cagemini.lifescience.service.QuizServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuizController {

    private QuizServiceImpl quizService;

    @Autowired
    public QuizController(QuizServiceImpl quizService) {
        this.quizService = quizService;
    }

    @GetMapping("quiz")
    public List<QuizDTO> getQuizByChapitre(@RequestParam Long chapitreId){
        return quizService.getQuizWithPropositionsByChapitreId(chapitreId);
    }
    @PostMapping("/quiz_proposal")
    public ApiResponse addQuizWithPropositions(@RequestBody QuizDTO quizDTO, @RequestParam Long chapitreId) {
        quizService.addQuizWithPropositions(quizDTO.getQuiz(), quizDTO.getPropositions(), chapitreId);
        return (new ApiResponse("QuizWithProspals added!"));
    }
    @PutMapping("/quiz/{chapitreId}/{quizId}")
    public ApiResponse updateQuiz(@PathVariable Long chapitreId, @PathVariable Long quizId, @RequestBody Quiz updatedQuiz){
        quizService.updateQuiz(chapitreId, quizId, updatedQuiz);
        return (new ApiResponse("Quiz updated"));
    }
    @PostMapping("/quiz")
    public ApiResponse addQuiz(@RequestParam Long chapitreId ,@RequestBody Quiz theQuiz) {
        theQuiz.setId(0L);
        quizService.save(chapitreId, theQuiz);
        return (new ApiResponse("Quiz added!"));
    }

    @DeleteMapping("/quiz/{theId}/{chapitreId}")
    public ApiResponse deleteQuiz(@PathVariable Long theId, @PathVariable Long chapitreId){
        quizService.deleteById(theId, chapitreId);
        return ( new ApiResponse("Deleted quiz id :" + theId));
    }
}
