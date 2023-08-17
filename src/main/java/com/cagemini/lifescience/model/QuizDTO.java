package com.cagemini.lifescience.model;

import com.cagemini.lifescience.entity.Proposition;
import com.cagemini.lifescience.entity.Quiz;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties({"chapitre"})
public class QuizDTO {
    private QuizInfo quiz;
    private List<PropositionInfo> propositions;

    public QuizDTO() {
    }

    public QuizDTO(QuizInfo quiz, List<PropositionInfo> propositions) {
        this.quiz = quiz;
        this.propositions = propositions;
    }

    public QuizInfo getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizInfo quiz) {
        this.quiz = quiz;
    }

    public List<PropositionInfo> getPropositions() {
        return propositions;
    }

    public void setPropositions(List<PropositionInfo> propositions) {
        this.propositions = propositions;
    }
}
