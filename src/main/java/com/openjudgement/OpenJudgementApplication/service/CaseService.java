package com.openjudgement.OpenJudgementApplication.service;

import com.openjudgement.OpenJudgementApplication.entity.Case;
import com.openjudgement.OpenJudgementApplication.entity.CaseState;
import com.openjudgement.OpenJudgementApplication.repository.CaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CaseService {
    private final CaseRepository caseRepository;
    private IdentityService identityService;

    public CaseService(CaseRepository caseRepository, IdentityService identityService) {
        this.caseRepository = caseRepository;
        this.identityService = identityService;
    }

    @Transactional
    public Case createCase(String title) {
        Case caze = caseRepository.save(new Case(title));
        identityService.AssignPartnerA(caze.getId());
        return caze;
    }
    @Transactional
    public Case invitePartner(UUID caseId) {
        Case caze = getCase(caseId);
        caze.transitionTo(CaseState.INVITED);
        return caze;
    }
    @Transactional(readOnly = true)
    public Case getCase(UUID caseId) {
        return caseRepository.findById(caseId).orElseThrow(()-> new IllegalArgumentException("Case not found!"));
    }
    @Transactional
    public Case openStatement(UUID caseId) {
        Case caze = getCase(caseId);
        caze.transitionTo(CaseState.OPENING_STATEMENTS);
        return caze;
    }
}
