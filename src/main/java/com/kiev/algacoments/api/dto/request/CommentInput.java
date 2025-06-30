package com.kiev.algacoments.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CommentInput(
    @NotBlank String text,
    @NotBlank String author
) {}