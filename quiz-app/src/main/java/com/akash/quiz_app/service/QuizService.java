package com.akash.quiz_app.service;


import com.akash.quiz_app.dto.requestDTO.CreateQuizRequest;
import com.akash.quiz_app.dto.responseDTO.QuizResponse;

import java.util.List;

public interface QuizService {
    QuizResponse createQuiz(CreateQuizRequest request);

    List<QuizResponse> getAllQuizzes();

    QuizResponse getQuizById(Long id);
}
