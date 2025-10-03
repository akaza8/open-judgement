package com.akash.quiz_app.dto.responseDTO;

public record ScoreResponse(
        int score,
        int total,
        double percentage
) {
}
