package com.example.authorWebsite.Services.Interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface SendResponseEntity {

    public ResponseEntity SendResponseEntity(String status, String message, HttpStatus httpStatus);
    public<T> ResponseEntity SendResponseEntityWithBody(String status, String message,HttpStatus httpStatus,T body );

}
