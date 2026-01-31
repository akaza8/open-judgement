package com.openjudgement.OpenJudgementApplication.casefile.api;

import com.openjudgement.OpenJudgementApplication.casefile.Case;
import com.openjudgement.OpenJudgementApplication.casefile.CaseState;

import java.time.Instant;
import java.util.UUID;

public record CaseResponse(
        UUID id,
        String title,
        CaseState state,
        Instant createdAt,
        Instant updatedAt
) {
    public static CaseResponse from(Case caze) {
        if(caze==null) throw new IllegalArgumentException("Case cannot be null");
        return new CaseResponse(
                caze.getId(),
                caze.getTitle(),
                caze.getState(),
                caze.getCreatedAt(),
                caze.getUpdatedAt()
        );
    }
}
