package com.monfauna.api.exception;

public class InvalidResourceException extends RuntimeException{
    public InvalidResourceException(String message) {
        super(message);
    }
}
