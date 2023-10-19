package com.psc.wktest.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IOException.class)
    public ResponseEntity<Object> handleIOException(IOException e) {
        e.printStackTrace();
        return new ResponseEntity<>("Ocorreu um erro ao tentar processar o arquivo.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
