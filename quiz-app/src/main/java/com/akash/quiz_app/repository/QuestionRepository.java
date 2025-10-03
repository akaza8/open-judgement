package com.akash.quiz_app.repository;

import com.akash.quiz_app.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
