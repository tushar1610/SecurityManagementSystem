package com.example.SecurityManagementSystem.exception;

public class GuardUserNotCreatedException extends Exception{

    public GuardUserNotCreatedException() {
        super();
    }

    public GuardUserNotCreatedException(String message) {
        super(message);
    }

    public GuardUserNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public GuardUserNotCreatedException(Throwable cause) {
        super(cause);
    }

    protected GuardUserNotCreatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
