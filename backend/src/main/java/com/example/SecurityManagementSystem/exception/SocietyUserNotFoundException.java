package com.example.SecurityManagementSystem.exception;

public class SocietyUserNotFoundException extends Exception{

    public SocietyUserNotFoundException() {
        super();
    }

    public SocietyUserNotFoundException(String message) {
        super(message);
    }

    public SocietyUserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SocietyUserNotFoundException(Throwable cause) {
        super(cause);
    }

    protected SocietyUserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
