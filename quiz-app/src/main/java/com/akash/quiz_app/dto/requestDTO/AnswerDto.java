package com.akash.quiz_app.dto.requestDTO;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AnswerDto(
    @NotNull(message = "Question ID is required")
    Long questionId,
    List<Long> selectedOptionIds,
    String textAnswer
) {}
