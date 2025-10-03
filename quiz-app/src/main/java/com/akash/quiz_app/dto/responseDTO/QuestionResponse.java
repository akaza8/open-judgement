package com.akash.quiz_app.dto.responseDTO;

import com.akash.quiz_app.entity.QuestionType;

import java.util.List;

public record QuestionResponse(
        Long id,
        String text,
        QuestionType type,
        List<OptionResponse> options
) {
}
