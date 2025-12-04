package com.assessment.quiz_service.controller;

import com.assessment.quiz_service.models.CreateQuizRequest;
import com.assessment.quiz_service.models.QuizResponse;
import com.assessment.quiz_service.models.SubmitQuizRequest;
import com.assessment.quiz_service.models.SubmitQuizResponse;
import com.assessment.quiz_service.service.QuizService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quizzes")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    public Long createQuiz(@RequestBody CreateQuizRequest request){
        return quizService.createQuiz(request);
    }

    @GetMapping
    public List<QuizResponse> getAllQuizzes(){
        return quizService.getAllQuizzes();
    }

    @PostMapping("/{quizId}/submit")
    public Object submitQuiz(@PathVariable Long quizId, @RequestBody SubmitQuizRequest request){
        return quizService.submitQuiz(quizId, request);
    }
}
