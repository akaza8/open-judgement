package com.akash.quiz_app.service;

import com.akash.quiz_app.dto.requestDTO.AddQuestionRequest;
import com.akash.quiz_app.dto.requestDTO.SubmitAnswerRequest;
import com.akash.quiz_app.dto.responseDTO.QuestionResponse;
import com.akash.quiz_app.dto.responseDTO.ScoreResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface QuestionService {
   ScoreResponse submitAnswer(Long quizID, SubmitAnswerRequest request);

QuestionResponse addQuestion(Long quizId, AddQuestionRequest request);

    List<QuestionResponse> getQuizQuestions(long id);
}
