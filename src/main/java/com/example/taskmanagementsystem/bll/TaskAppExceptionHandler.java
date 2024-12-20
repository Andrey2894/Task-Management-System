package com.example.taskmanagementsystem.bll;

import com.example.taskmanagementsystem.exception.IdNotFoundException;
import com.example.taskmanagementsystem.exception.TitleIsNullException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
