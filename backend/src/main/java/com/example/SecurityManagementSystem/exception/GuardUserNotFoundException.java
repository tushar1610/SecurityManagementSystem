package com.example.SecurityManagementSystem.exception;

public class GuardUserNotFoundException extends Exception{

    public GuardUserNotFoundException() {
        super();
    }

    public GuardUserNotFoundException(String message) {
        super(message);
    }

    public GuardUserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GuardUserNotFoundException(Throwable cause) {
        super(cause);
    }

    protected GuardUserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
