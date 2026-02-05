package com.openjudgement.OpenJudgementApplication.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "case_roles", uniqueConstraints = @UniqueConstraint(columnNames = {"case_id", "role"}))
public class CaseRole {
    @Id
    @GeneratedValue
    private UUID id;
    private UUID caseId;
    private UUID anonymousId;
    @Enumerated(EnumType.STRING)
    private RoleType role;
    private Instant createdAt;
    protected CaseRole(){}

    public CaseRole(UUID caseId, RoleType role){
        this.caseId = caseId;
        this.role = role;
        this.anonymousId = UUID.randomUUID();
        this.createdAt = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public UUID getCaseId() {
        return caseId;
    }

    public UUID getAnonymousId() {
        return anonymousId;
    }

    public RoleType getRole() {
        return role;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
