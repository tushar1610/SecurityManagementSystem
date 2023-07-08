package com.example.SecurityManagementSystem.exception;

public class VisitorNotCreatedException extends Exception{

    public VisitorNotCreatedException() {
        super();
    }

    public VisitorNotCreatedException(String message) {
        super(message);
    }

    public VisitorNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public VisitorNotCreatedException(Throwable cause) {
        super(cause);
    }

    protected VisitorNotCreatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
