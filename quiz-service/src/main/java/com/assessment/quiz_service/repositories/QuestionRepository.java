package com.assessment.quiz_service.repositories;

import com.assessment.quiz_service.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
