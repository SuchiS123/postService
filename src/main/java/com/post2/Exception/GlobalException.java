package com.post2.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler (ResourceNotFound.class)
    public ResponseEntity<ErrorDetails> ResourceExceptionHandler(ResourceNotFound e, WebRequest request)
    {
        ErrorDetails errorDetails = new ErrorDetails(new Date(System.currentTimeMillis()),
                 e.getMessage(),request.getDescription(true));
        return new ResponseEntity<>(errorDetails, HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDetails> defaultExceptionHandler(Exception e, WebRequest request)
    {
        ErrorDetails errorDetails = new ErrorDetails(new Date(System.currentTimeMillis()),
                e.getMessage(), request.getDescription(true));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
