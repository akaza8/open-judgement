package com.akash.quiz_app.repository;

import com.akash.quiz_app.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
