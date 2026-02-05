package com.openjudgement.OpenJudgementApplication.repository;

import com.openjudgement.OpenJudgementApplication.entity.CaseRole;
import com.openjudgement.OpenJudgementApplication.entity.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CaseRoleRepository extends JpaRepository<CaseRole, UUID> {
    Optional<CaseRole> findCaseIdByRole(UUID caseId, RoleType role);
}
