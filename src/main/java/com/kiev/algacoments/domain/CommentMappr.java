package com.kiev.algacoments.domain;

import com.kiev.algacoments.api.dto.request.CommentInput;
import com.kiev.algacoments.domain.model.Comment;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CommentMappr {

    public static Comment commentInputToComment(CommentInput commentInput, UUID uuid) {
        return Comment.builder()
                .id(uuid)
                .author(commentInput.author())
                .text(commentInput.text())
                .build();
    }
}
