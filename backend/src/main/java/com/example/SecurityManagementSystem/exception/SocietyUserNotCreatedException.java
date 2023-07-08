package com.example.SecurityManagementSystem.exception;

public class SocietyUserNotCreatedException extends Exception{

    public SocietyUserNotCreatedException() {
        super();
    }

    public SocietyUserNotCreatedException(String message) {
        super(message);
    }

    public SocietyUserNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SocietyUserNotCreatedException(Throwable cause) {
        super(cause);
    }

    protected SocietyUserNotCreatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
