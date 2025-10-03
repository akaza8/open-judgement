package com.akash.quiz_app.dto.responseDTO;

public record QuizResponse(
        Long id,
        String title,
        int questionCount
) {
}
