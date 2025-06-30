package com.kiev.algacoments.exception;

public class GatewayTimeoutException extends RuntimeException {
    public GatewayTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}