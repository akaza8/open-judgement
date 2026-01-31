package com.openjudgement.OpenJudgementApplication.casefile;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CaseService {
    private final CaseRepository caseRepository;

    public CaseService(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    @Transactional
    public Case createCase(String title) {
        Case caze = new Case(title);
        return caseRepository.save(caze);
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
