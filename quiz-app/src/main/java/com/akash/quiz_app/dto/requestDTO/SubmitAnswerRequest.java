package com.akash.quiz_app.dto.requestDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SubmitAnswerRequest(
        @Valid
        @NotNull(message = "Answers are required")
    List<AnswerDto> answers
)
{}
