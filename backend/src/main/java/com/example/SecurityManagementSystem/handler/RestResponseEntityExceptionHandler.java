package com.example.SecurityManagementSystem.handler;

import com.example.SecurityManagementSystem.exception.*;
import com.example.SecurityManagementSystem.handlerEntity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SocietyUserNotFoundException.class)
    public ResponseEntity<ErrorMessage> societyUserNotFoundException(SocietyUserNotFoundException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorMessage);

    }

    @ExceptionHandler(SocietyUserNotCreatedException.class)
    public ResponseEntity<ErrorMessage> societyUserNotCreatedException(SocietyUserNotCreatedException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorMessage);

    }

    @ExceptionHandler(GuardUserNotFoundException.class)
    public ResponseEntity<ErrorMessage> guardUserNotFoundException(GuardUserNotFoundException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorMessage);

    }

    @ExceptionHandler(GuardUserNotCreatedException.class)
    public ResponseEntity<ErrorMessage> guardUserNotCreatedException(GuardUserNotCreatedException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorMessage);

    }

    @ExceptionHandler(VisitorNotFoundException.class)
    public ResponseEntity<ErrorMessage> visitorNotFoundException(VisitorNotFoundException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorMessage);

    }

    @ExceptionHandler(NotificationNotFoundException.class)
    public ResponseEntity<ErrorMessage> notificationNotFoundException(NotificationNotFoundException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorMessage);

    }

    @ExceptionHandler(VisitorNotCreatedException.class)
    public ResponseEntity<ErrorMessage> visitorNotCreatedException(VisitorNotCreatedException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorMessage);

    }

    @ExceptionHandler(VisitorAlreadyExitException.class)
    public ResponseEntity<ErrorMessage> visitorAlreadyExitException(VisitorAlreadyExitException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.UNAUTHORIZED, exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(errorMessage);

    }

    @ExceptionHandler(NoSuchUserException.class)
    public ResponseEntity<ErrorMessage> noSuchUserException(NoSuchUserException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorMessage);

    }

    @ExceptionHandler(UserNotAuthorizedException.class)
    public ResponseEntity<ErrorMessage> userNotAuthorizedException(UserNotAuthorizedException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.UNAUTHORIZED, exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(errorMessage);

    }

}
