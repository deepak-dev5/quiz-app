package com.assessment.quiz_service.controller;

import com.assessment.quiz_service.models.CreateQuizRequest;
import com.assessment.quiz_service.models.QuizResponse;
import com.assessment.quiz_service.models.SubmitQuizRequest;
import com.assessment.quiz_service.models.SubmitQuizResponse;
import com.assessment.quiz_service.service.QuizService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quizzes")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    public Long createQuiz(@RequestBody CreateQuizRequest request){
        log.info("Create Quiz Request: Title = {}, Questions = {}", request.getTitle(), request.getQuestions());
        return quizService.createQuiz(request);
    }

    @GetMapping
    public List<QuizResponse> getAllQuizzes(){
        return quizService.getAllQuizzes();
    }

    @GetMapping("{id}")
    public List<QuizResponse> getRandomQuiz(@PathVariable Integer id){
        return quizService.getQuizById(Long.valueOf(id));
    }

    @PostMapping("/{quizId}/submit")
    public SubmitQuizResponse submitQuiz(@PathVariable Long quizId, @RequestBody SubmitQuizRequest request){
        log.info("Submit Quiz Request for quizId: {} with answers: {}", quizId, request.getAnswers());
        return quizService.submitQuiz(quizId, request);
    }
}
