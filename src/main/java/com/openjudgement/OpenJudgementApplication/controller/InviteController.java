package com.openjudgement.OpenJudgementApplication.controller;

import com.openjudgement.OpenJudgementApplication.service.IdentityService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/invites")
public class InviteController {
    private final IdentityService identityService;

    public InviteController(IdentityService identityService) {
        this.identityService = identityService;
    }

    @PostMapping("/caseId")
    public ResponseEntity<String> create(@PathVariable String caseId) {
        return ResponseEntity.ok(identityService.createInvite(UUID.fromString(caseId)).getToken());
    }

    @PostMapping("/accept")
    public ResponseEntity<String> accept(@RequestParam String token) {
        return ResponseEntity.ok(
                identityService.acceptInvite(token).getAnonymousId().toString()
        );
    }

}
