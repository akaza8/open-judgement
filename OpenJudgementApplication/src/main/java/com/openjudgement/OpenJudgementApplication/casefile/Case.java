package com.openjudgement.OpenJudgementApplication.casefile;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "cases")
public class Case {
    private UUID id;
    private String title;
    @Enumerated(EnumType.STRING)
    private CaseState state;
    private Instant createdAt;
    private Instant updatedAt;

    protected Case() {
    }

    public void transitionTo(CaseState target){
        if (!state.canTransitionTo(target)) {
            throw new IllegalStateException(
                    "Invalid transition from " + state + " to " + target
            );
        }
        this.state = target;
        this.updatedAt = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public CaseState getState() {
        return state;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
