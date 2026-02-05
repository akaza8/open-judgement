package com.openjudgement.OpenJudgementApplication.service;

import com.openjudgement.OpenJudgementApplication.entity.CaseRole;
import com.openjudgement.OpenJudgementApplication.entity.InvitationToken;
import com.openjudgement.OpenJudgementApplication.entity.RoleType;
import com.openjudgement.OpenJudgementApplication.repository.CaseRoleRepository;
import com.openjudgement.OpenJudgementApplication.repository.InvitationTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class IdentityService {
    private final CaseRoleRepository roleRepo;
    private final InvitationTokenRepository tokenRepo;

    public IdentityService(CaseRoleRepository roleRepo, InvitationTokenRepository tokenRepo) {
        this.roleRepo = roleRepo;
        this.tokenRepo = tokenRepo;
    }
    @Transactional
    public CaseRole AssignPartnerA(UUID caseId) {
        return roleRepo.save(new CaseRole(caseId, RoleType.PARTNER_A));
    }

    @Transactional
    public InvitationToken createInvite(UUID caseId){
        String token = UUID.randomUUID().toString();
        InvitationToken invite = new InvitationToken(caseId, token, Instant.now().plus(48, ChronoUnit.HOURS));
        return tokenRepo.save(invite);
    }

    public CaseRole acceptInvite(String token) {
        InvitationToken invite = tokenRepo.findByToken(token).orElseThrow(() -> new IllegalArgumentException("Invalid token"));
        if(invite.isUsed() || invite.getExpiresAt().isBefore(Instant.now())) {
            throw new IllegalStateException("Token not Found");
        }
        CaseRole role = new CaseRole(invite.getCaseId(), RoleType.PARTNER_B);
        invite.markUsed();
        tokenRepo.save(invite);
        roleRepo.save(role);
        return role;
    }
}
