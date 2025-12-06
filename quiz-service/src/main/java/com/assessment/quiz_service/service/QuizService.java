package com.assessment.quiz_service.service;

import com.assessment.quiz_service.entities.Options;
import com.assessment.quiz_service.entities.Question;
import com.assessment.quiz_service.entities.Quiz;
import com.assessment.quiz_service.models.*;
import com.assessment.quiz_service.repositories.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Long createQuiz(CreateQuizRequest request){
        Quiz quiz = new Quiz();
        quiz.setTitle(request.getTitle());

        List<Question> questionEntities = new ArrayList<>();

        for(QuestionRequest q: request.getQuestions()){
            Question question = new Question();
            question.setText(q.getText());
            question.setType(q.getType());
            question.setCorrectAnswer(q.getCorrectAnswer());
            question.setQuiz(quiz);

            if(q.getOptions() != null){
                List<Options> options = q.getOptions().stream()
                        .map(opt->{
                            Options o = new Options();
                            o.setText(opt);
                            o.setQuestion(question);
                            return o;
                        }).collect(Collectors.toList());
                question.setOptions(options);
            }
            questionEntities.add(question);
        }
        quiz.setQuestions(questionEntities);
        return quizRepository.save(quiz).getId();
    }

    public List<QuizResponse> getAllQuizzes(){
        List<Quiz> quizzes = quizRepository.findAll();
        return quizzes.stream().map(this::toQuizResponse).collect(Collectors.toList());
    }

    public List<QuizResponse> getQuizById(Long id){
        List<Quiz> quizzes = quizRepository.findAllById(Collections.singleton(id));
        return quizzes.stream().map(this::toQuizResponse).collect(Collectors.toList());
    }

    public SubmitQuizResponse submitQuiz(Long quizId, SubmitQuizRequest request){
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(()-> new RuntimeException("Quiz not found with id: " + quizId));

        Map<Long, String> answers = request.getAnswers();
        if(answers==null){
            answers = new HashMap<>();
        }
        int score=0;
        int totalQuestions = quiz.getQuestions().size();

        List<QuestionResponse> questionsWithCorrectAnswers = new ArrayList<>();

        for(Question q : quiz.getQuestions()){
            String userAnswer = answers.get(q.getId());
            if(userAnswer == null){
                userAnswer = "";
            }

            if(q.getCorrectAnswer() != null && q.getCorrectAnswer().equalsIgnoreCase(userAnswer)){
                score++;
            }

            QuestionResponse questionResponse = new QuestionResponse();
            questionResponse.setId(q.getId());
            questionResponse.setText(q.getText());
            questionResponse.setType(q.getType());

            if("MCQ".equalsIgnoreCase(q.getType()) && q.getOptions() != null){
                questionResponse.setOptions(q.getOptions().stream()
                        .map(Options::getText)
                        .collect(Collectors.toList()));
            }
            questionResponse.setCorrectAnswer(q.getCorrectAnswer());

            questionsWithCorrectAnswers.add(questionResponse);
        }

        return new SubmitQuizResponse(score, totalQuestions, questionsWithCorrectAnswers);
    }

    private QuizResponse toQuizResponse(Quiz quiz){
        QuizResponse response = new QuizResponse();
        response.setId(quiz.getId());
        response.setTitle(quiz.getTitle());
        List<QuestionResponse> questions = quiz.getQuestions().stream()
                .map(q -> {
                    QuestionResponse qr = new QuestionResponse();
                    qr.setId(q.getId());
                    qr.setText(q.getText());
                    qr.setType(q.getType());
                    qr.setCorrectAnswer(q.getCorrectAnswer());

                    if("MCQ".equalsIgnoreCase(q.getType()) && q.getOptions() != null){
                        qr.setOptions(q.getOptions().stream()
                                .map(Options::getText)
                                .collect(Collectors.toList()));
                    }
                    return qr;
                }).collect(Collectors.toList());
        response.setQuestions(questions);
        return response;
    }
}
