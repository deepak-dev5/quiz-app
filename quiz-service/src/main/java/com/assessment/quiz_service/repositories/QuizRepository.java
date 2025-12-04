package com.assessment.quiz_service.repositories;

import com.assessment.quiz_service.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
