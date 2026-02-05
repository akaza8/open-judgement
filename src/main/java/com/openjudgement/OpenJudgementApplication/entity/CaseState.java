package com.openjudgement.OpenJudgementApplication.entity;

public enum CaseState {
    DRAFT,
    INVITED,
    OPENING_STATEMENTS,
    ARCHIVED;

    public boolean canTransitionTo(CaseState target) {
        return switch (this) {
            case DRAFT -> target == INVITED;
            case INVITED -> target == OPENING_STATEMENTS || target == ARCHIVED;
            case OPENING_STATEMENTS -> target == ARCHIVED;
            case ARCHIVED -> false;
        };
    }
}
