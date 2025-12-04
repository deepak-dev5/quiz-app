package com.assessment.quiz_service.models;

import java.util.List;

public class CreateQuizRequest {

    private String title;
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
