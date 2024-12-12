package com.example.taskmanagementsystem.handler;

import com.example.taskmanagementsystem.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TaskAppExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    protected ResponseEntity<MyException> handleIdNotFoundException() {
        return new ResponseEntity<>(new MyException("Id is not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TitleIsNullException.class)
    protected ResponseEntity<MyException> handleTitleIsNullException() {
        return new ResponseEntity<>(new MyException("Title can't be empty"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameIsNullException.class)
    protected ResponseEntity<MyException> handleUsernameIsNullException() {
        return new ResponseEntity<>(new MyException("Username can't be empty"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordIsNullException.class)
    protected ResponseEntity<MyException> handlePasswordIsNullException() {
        return new ResponseEntity<>(new MyException("Password can't be empty"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    protected ResponseEntity<MyException> handleUsernameNotFoundException() {
        return new ResponseEntity<>(new MyException("Username is not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    protected ResponseEntity<MyException> handleUsernameAlreadyExistsException() {
        return new ResponseEntity<>(new MyException("Username already exists"), HttpStatus.BAD_REQUEST);
    }

    private static class MyException {
        private String message;
        public MyException(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
    }
}
