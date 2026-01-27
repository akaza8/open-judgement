package com.akash.quiz_app.controller;

import com.akash.quiz_app.dto.requestDTO.CreateQuizRequest;
import com.akash.quiz_app.dto.requestDTO.SubmitAnswerRequest;
import com.akash.quiz_app.dto.responseDTO.QuestionResponse;
import com.akash.quiz_app.dto.responseDTO.QuizResponse;
import com.akash.quiz_app.dto.responseDTO.ScoreResponse;
import com.akash.quiz_app.service.QuestionService;
import com.akash.quiz_app.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
@Tag(name = "Quiz Controller", description = "Quiz Management")
public class QuizController {
    private final QuizService quizService;
    private final QuestionService questionService;
    public QuizController(QuizService quizService, QuestionService questionService) {
        this.quizService = quizService;
        this.questionService = questionService;
    }

    @PostMapping
    @Operation(summary = "Create a new quiz")
    public ResponseEntity<QuizResponse> createQuiz(@Valid @RequestBody CreateQuizRequest request) {
        QuizResponse quizResponse = quizService.createQuiz(request);
        return new ResponseEntity<>(quizResponse, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all quizzes")
    public ResponseEntity<List<QuizResponse>> getAllQuizzes() {
        List<QuizResponse> quizResponses = quizService.getAllQuizzes();
        return new ResponseEntity<>(quizResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a quiz by ID")
    public ResponseEntity<QuizResponse> getQuizById(@PathVariable Long id) {
        QuizResponse quizResponse = quizService.getQuizById(id);
        return new ResponseEntity<>(quizResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}/questions")
    @Operation(summary = "Get all questions of a quiz")
    public ResponseEntity<List<QuestionResponse>> getQuizQuestions(@PathVariable long id) {
        List<QuestionResponse> questionResponses = questionService.getQuizQuestions(id);
        return new ResponseEntity<>(questionResponses, HttpStatus.OK);
    }

    @PostMapping("/{quizId}/submit")
    @Operation(summary = "Submit answer and get score")
    public ResponseEntity<ScoreResponse> submitAnswerRequest(@PathVariable Long quizId ,@Valid @RequestBody SubmitAnswerRequest request) {
        ScoreResponse response=questionService.submitAnswer(quizId, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
