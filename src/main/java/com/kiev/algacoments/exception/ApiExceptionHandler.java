package com.kiev.algacoments.exception;

import com.kiev.algacoments.domain.exception.CommentNotApprovedException;
import com.kiev.algacoments.domain.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFound(ResourceNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Recurso não encontrado");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(CommentNotApprovedException.class)
    public ProblemDetail handleCommentNotApproved(CommentNotApprovedException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        problemDetail.setTitle("Comentário não pôde ser processado");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(BadGatewayException.class)
    public ProblemDetail handleBadGateway(BadGatewayException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_GATEWAY, e.getMessage());
        problemDetail.setTitle("Erro de comunicação");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(GatewayTimeoutException.class)
    public ProblemDetail handleGatewayTimeout(GatewayTimeoutException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.GATEWAY_TIMEOUT, e.getMessage());
        problemDetail.setTitle("Timeout na comunicação");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}