package com.scaler.productapi.controller;

import com.scaler.productapi.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice

public class ExceptionAdvices {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> error(Exception exception){
return  new ResponseEntity<>("Nahi chala dekh phir se", HttpStatus.OK);
    }
}
