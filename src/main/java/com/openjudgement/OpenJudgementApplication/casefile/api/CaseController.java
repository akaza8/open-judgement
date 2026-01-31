package com.openjudgement.OpenJudgementApplication.casefile.api;

import com.openjudgement.OpenJudgementApplication.casefile.Case;
import com.openjudgement.OpenJudgementApplication.casefile.CaseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/cases")
public class CaseController {
    private final CaseService caseService;

    public CaseController(CaseService caseService) {
        this.caseService = caseService;
    }

    @PostMapping
    public ResponseEntity<CaseResponse> create(@Valid @RequestBody CreateCaseRequest request){
        Case createdCase = caseService.createCase(request.title());
        if(createdCase==null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(CaseResponse.from(createdCase));
    }
    @PostMapping("/{id}/invite")
    public ResponseEntity<CaseResponse> invite(@PathVariable UUID id) {
        return ResponseEntity.ok(CaseResponse.from(caseService.invitePartner(id)));
    }
    @GetMapping("/{id}")
    public ResponseEntity<CaseResponse> get(@PathVariable UUID id) {
        return ResponseEntity.ok(CaseResponse.from(caseService.getCase(id)));
    }
    @PostMapping("/{id}/open")
    public ResponseEntity<CaseResponse> openStatement(@PathVariable UUID id) {
        return ResponseEntity.ok(CaseResponse.from(caseService.openStatement(id)));
    }
}
