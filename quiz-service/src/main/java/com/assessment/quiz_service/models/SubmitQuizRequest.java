package com.assessment.quiz_service.models;

import java.util.Map;

public class SubmitQuizRequest {
    private Map<Long, String> answers;

    public Map<Long, String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<Long, String> answers) {
        this.answers = answers;
    }
}


