package com.kiev.algacoments.domain.service;

import com.kiev.algacoments.api.client.IModerationClient;
import com.kiev.algacoments.api.dto.request.ModerationInput;
import com.kiev.algacoments.domain.exception.CommentNotApprovedException;
import com.kiev.algacoments.domain.exception.ResourceNotFoundException;
import com.kiev.algacoments.domain.model.CommentOutput;
import com.kiev.algacoments.domain.repository.CommentRepository;
import com.kiev.algacoments.domain.model.Comment;
import com.kiev.algacoments.api.dto.request.CommentInput;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.kiev.algacoments.domain.CommentMappr.commentInputToComment;
import static com.kiev.algacoments.domain.model.CommentOutput.fromEntity;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final IModerationClient moderationClient;

    public CommentService(CommentRepository commentRepository, IModerationClient moderationClient) {
        this.commentRepository = commentRepository;
        this.moderationClient = moderationClient;
    }

    @Transactional
    public CommentOutput createComment(CommentInput input) {
        var newId = UUID.randomUUID();
        var moderationInput = new ModerationInput(input.text(), newId.toString());
        var moderationResponse = moderationClient.moderate(moderationInput);
        if (moderationResponse.approved()) {
            var newComment = commentInputToComment(input, newId);
            var savedComment = commentRepository.save(newComment);
            return fromEntity(savedComment);
        }
        throw new CommentNotApprovedException("Comentário rejeitado: " + moderationResponse.reason());
    }

    public CommentOutput findById(UUID id) {
        var comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comentário com ID " + id + " não encontrado."));
        return fromEntity(comment);
    }

    public Page<CommentOutput> findAll(Pageable pageable) {
        Page<Comment> commentPage = commentRepository.findAll(pageable);
        return commentPage.map(CommentOutput::fromEntity);
    }
}