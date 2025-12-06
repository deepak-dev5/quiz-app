package com.assessment.quiz_service.models;

import java.util.List;

public class SubmitQuizResponse {
    private int score;
    private int total;
    private List<QuestionResponse> questionsWithCorrectAnswers;

    public SubmitQuizResponse(int score, int totalQuestions, List<QuestionResponse> questionsWithCorrectAnswers) {
        this.score = score;
        this.total = totalQuestions;
        this.questionsWithCorrectAnswers = questionsWithCorrectAnswers;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<QuestionResponse> getQuestionsWithCorrectAnswers() {
        return questionsWithCorrectAnswers;
    }

    public void setQuestionsWithCorrectAnswers(List<QuestionResponse> questionsWithCorrectAnswers) {
        this.questionsWithCorrectAnswers = questionsWithCorrectAnswers;
    }


}
