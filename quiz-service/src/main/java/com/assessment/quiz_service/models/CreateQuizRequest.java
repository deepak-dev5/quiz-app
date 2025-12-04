package com.assessment.quiz_service.models;

import java.util.List;

public class CreateQuizRequest {

    private String title;
    private List<CreateQuizRequest> questions;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CreateQuizRequest> getQuestions() {
        return questions;
    }

    public void setQuestions(List<CreateQuizRequest> questions) {
        this.questions = questions;
    }
}
