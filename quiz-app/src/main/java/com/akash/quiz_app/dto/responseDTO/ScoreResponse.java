package com.akash.quiz_app.dto.responseDTO;

import lombok.Builder;

@Builder
public record ScoreResponse(
        int score,
        int total,
        double percentage
) {
}
