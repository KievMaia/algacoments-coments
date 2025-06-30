package com.kiev.algacoments.domain.exception;

public class CommentNotApprovedException extends RuntimeException {
    public CommentNotApprovedException(String message) {
        super(message);
    }
}