package com.assessment.quiz_service.models;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CreateQuizRequest {

    @NotNull(message = "Title cannot be null")
    private String title;

    @NotNull(message = "Questions cannot be null")
    private List<QuestionRequest> questions;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<QuestionRequest> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionRequest> questions) {
        this.questions = questions;
    }
}
