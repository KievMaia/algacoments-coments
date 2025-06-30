package com.kiev.algacoments.domain.model;

import java.time.OffsetDateTime;
import java.util.UUID;

public record CommentOutput(
    UUID id,
    String text,
    String author,
    OffsetDateTime createdAt
) {
    public static CommentOutput fromEntity(Comment comment) {
        return new CommentOutput(
            comment.getId(),
            comment.getText(),
            comment.getAuthor(),
            comment.getCreatedAt()
        );
    }
}