package com.assessment.quiz_service.models;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class QuestionRequest {

    @NotNull(message = "Question text cannot be null")
    private String text;

    @NotNull(message = "Question type cannot be null")
    private String type;

    @NotNull(message = "Options cannot be null")
    private List<String> options;

    @NotNull(message = "Correct answer cannot be null")
    private String correctAnswer;

    @Override
    public String toString() {
        return "QuestionRequest{" +
                "text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", options=" + options +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }
}
