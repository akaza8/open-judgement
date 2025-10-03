package com.akash.quiz_app.dto.requestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record OptionDTO(
        @NotBlank(message = "Option text is required")
        @Size(max = 300, message = "Option text must be between 1 and 100 characters")
        String text,
        boolean isCorrect
) {}
