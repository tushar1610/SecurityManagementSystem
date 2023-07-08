package com.example.SecurityManagementSystem.exception;

public class VisitorAlreadyExitException extends Exception{

    public VisitorAlreadyExitException() {
        super();
    }

    public VisitorAlreadyExitException(String message) {
        super(message);
    }

    public VisitorAlreadyExitException(String message, Throwable cause) {
        super(message, cause);
    }

    public VisitorAlreadyExitException(Throwable cause) {
        super(cause);
    }

    protected VisitorAlreadyExitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
