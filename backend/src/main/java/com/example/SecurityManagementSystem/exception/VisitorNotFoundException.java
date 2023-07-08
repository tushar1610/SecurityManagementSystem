package com.example.SecurityManagementSystem.exception;

public class VisitorNotFoundException extends Exception{

    public VisitorNotFoundException() {
        super();
    }

    public VisitorNotFoundException(String message) {
        super(message);
    }

    public VisitorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public VisitorNotFoundException(Throwable cause) {
        super(cause);
    }

    protected VisitorNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
