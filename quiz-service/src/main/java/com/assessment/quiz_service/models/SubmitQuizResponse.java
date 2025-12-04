package com.assessment.quiz_service.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Map;

public class SubmitQuizResponse {
    private int score;
    private int total;
    private Map<Long, String> correctAnswers;

    public SubmitQuizResponse(int score, int totalQuestions, Map<Long, String> correctAnswers) {
        this.score = score;
        this.total = totalQuestions;
        this.correctAnswers = correctAnswers;
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

    public Map<Long, String> getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(Map<Long, String> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }
}
