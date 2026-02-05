package com.openjudgement.OpenJudgementApplication.dto.case_dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCaseRequest (
    @NotBlank
    String title
){}
