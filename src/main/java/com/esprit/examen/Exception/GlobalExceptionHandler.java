package com.esprit.examen.Exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.BindException;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RessourceDuplicated.class)
    public ResponseEntity<?> ressourceDuplicated(RessourceDuplicated ressourceDuplicated, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),ressourceDuplicated.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.FOUND);
    }

    @ExceptionHandler(RessourceNotFound.class)
    public ResponseEntity<?> ressourceNotFound(RessourceNotFound ressourceNotFound){
        ErrorDetails errorDetails = new ErrorDetails(ressourceNotFound.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception exception,WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),"Service indisponible",webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

/*
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

       // List<String> errors = new ArrayList<String>();
        Map<String, String> map = new HashMap<String, String>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            //errors.add(error.getField() + " -> " + error.getDefaultMessage());
            map.put(error.getField(),error.getDefaultMessage());
        }

        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            map.put(error.getObjectName(),error.getDefaultMessage());
            //errors.add(error.getObjectName() + " -> " + error.getDefaultMessage());
        }

        ErrorDetails apiError = new ErrorDetails(new Date(), ex.getMessage(), map);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }*/
}
