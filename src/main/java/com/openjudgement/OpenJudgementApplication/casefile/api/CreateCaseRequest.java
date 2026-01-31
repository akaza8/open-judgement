package com.openjudgement.OpenJudgementApplication.casefile.api;

import jakarta.validation.constraints.NotBlank;

public record CreateCaseRequest (
    @NotBlank
    String title
){}
