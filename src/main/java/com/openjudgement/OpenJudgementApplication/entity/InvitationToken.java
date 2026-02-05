package com.openjudgement.OpenJudgementApplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "invitation_tokens")
public class InvitationToken {
    @Id
    @GeneratedValue
    private UUID id;
    private UUID caseId;
    private String token;
    private Instant expiresAt;
    private boolean isUsed;

    protected InvitationToken(){}

    public InvitationToken(UUID caseId, String token, Instant expiresAt){
        this.caseId = caseId;
        this.token = token;
        this.expiresAt = expiresAt;
        this.isUsed = false;
    }
    public void markUsed(){
        this.isUsed = true;
    }

    public UUID getId() {
        return id;
    }

    public UUID getCaseId() {
        return caseId;
    }

    public String getToken() {
        return token;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public boolean isUsed() {
        return isUsed;
    }
}
