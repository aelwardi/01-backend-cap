package com.cagemini.lifescience.model;

import com.cagemini.lifescience.entity.Proposition;
import com.cagemini.lifescience.entity.Quiz;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties({"chapitre"})
public class QuizDTO {
    private Quiz quiz;
    private List<Proposition> propositions;

    public QuizDTO() {
    }

    public QuizDTO(Quiz quiz, List<Proposition> propositions) {
        this.quiz = quiz;
        this.propositions = propositions;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public List<Proposition> getPropositions() {
        return propositions;
    }

    public void setPropositions(List<Proposition> propositions) {
        this.propositions = propositions;
    }
}
