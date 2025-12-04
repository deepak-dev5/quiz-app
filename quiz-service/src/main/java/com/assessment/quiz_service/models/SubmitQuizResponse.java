package com.assessment.quiz_service.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Map;

public class SubmitQuizResponse {
    private int score;
    private int total;
    private String message;

    public SubmitQuizResponse(int score, int totalQuestions, String message) {
        this.score = score;
        this.total = totalQuestions;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage() {
        this.message = message;
    }
}
