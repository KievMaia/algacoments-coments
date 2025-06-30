package com.kiev.algacoments.api.controller;

import com.kiev.algacoments.api.dto.request.CommentInput;
import com.kiev.algacoments.domain.model.CommentOutput;
import com.kiev.algacoments.domain.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentOutput> create(@Valid @RequestBody CommentInput commentInput) {
        var createdComment = commentService.createComment(commentInput);
        return ResponseEntity.ok().body(createdComment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentOutput> getById(@PathVariable UUID id) {
        var comment = commentService.findById(id);
        return ResponseEntity.ok(comment);
    }

    @GetMapping
    public ResponseEntity<Page<CommentOutput>> getAll(Pageable pageable) {
        var commentsPage = commentService.findAll(pageable);
        return ResponseEntity.ok(commentsPage);
    }
}