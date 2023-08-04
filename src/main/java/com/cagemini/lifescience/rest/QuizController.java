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

    @PostMapping("/quiz")
    public Quiz addQuiz(@RequestParam Long chapitreId ,@RequestBody Quiz theQuiz) {
        theQuiz.setId(0L);
        Quiz dbQuiz = quizService.save(chapitreId, theQuiz);
        return dbQuiz;
    }

    @PutMapping("/quiz/{chapitreId}/{quizId}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable Long chapitreId, @PathVariable Long quizId, @RequestBody Quiz updatedQuiz){
        Quiz dbQuiz = quizService.updateQuiz(chapitreId, quizId, updatedQuiz);
        return ResponseEntity.ok(dbQuiz);
    }

    @DeleteMapping("/quiz/{theId}/{chapitreId}")
    public ApiResponse deleteQuiz(@PathVariable Long theId, @PathVariable Long chapitreId){
        quizService.deleteById(theId, chapitreId);
        return ( new ApiResponse("Deleted quiz id :" + theId));
    }

    @PostMapping("/quiz_proposal")
    public Quiz addQuizWithPropositions(@RequestBody QuizDTO quizDTO, @RequestParam Long chapitreId) {
        return quizService.addQuizWithPropositions(quizDTO.getQuiz(), quizDTO.getPropositions(), chapitreId);
    }

    @GetMapping("quiz")
    public List<QuizDTO> getQuizByChapitre(@RequestParam Long chapitreId){
        return quizService.getQuizWithPropositionsByChapitreId(chapitreId);
    }
}
