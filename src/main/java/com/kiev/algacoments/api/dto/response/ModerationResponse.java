package com.kiev.algacoments.api.dto.response;

public record ModerationResponse(
    boolean approved,
    String reason
) {}