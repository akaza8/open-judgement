package com.akash.quiz_app.dto.requestDTO;

import com.akash.quiz_app.entity.QuestionType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record AddQuestionRequest(
        @NotBlank(message = "Question text is required") String text,
        @NotNull(message = "Question type is required") QuestionType type,
        @NotNull(message = "Options are required")
        @Size(min = 1, message = "At least one option is required")
        @Valid List<OptionDTO> options
) {
}

