package com.akash.quiz_app.controller;

import com.akash.quiz_app.dto.requestDTO.AddQuestionRequest;
import com.akash.quiz_app.dto.responseDTO.QuestionResponse;
import com.akash.quiz_app.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quizzes/{quizId}/questions")
@RequiredArgsConstructor
@Tag(name = "Question Controller", description = "API for question management")
public class QuestionController {
    private final QuestionService questionService;
    @PostMapping
    @Operation(summary = "Create a new question")
    public ResponseEntity<QuestionResponse> addQuestion(@PathVariable Long quizId, @Valid @RequestBody AddQuestionRequest request) {
        QuestionResponse response = questionService.addQuestion(quizId, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
